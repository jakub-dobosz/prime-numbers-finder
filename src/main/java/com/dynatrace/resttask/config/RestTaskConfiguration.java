package com.dynatrace.resttask.config;

import com.dynatrace.resttask.client.DynatraceClient;
import com.dynatrace.resttask.client.DynatraceRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestTaskConfiguration {

    private RestTaskProperties properties;

    @Autowired
    RestTaskConfiguration(RestTaskProperties properties) {
        this.properties = properties;
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.basicAuthorization(properties.getLogin(), properties.getPass()).build();
    }

    @Bean
    DynatraceClient dynatraceRestClient(RestTemplate restTemplate) {
        return new DynatraceRestClient(
                restTemplate,
                properties.getDataEndpoint(),
                properties.getTestEndpoint()
        );
    }
}

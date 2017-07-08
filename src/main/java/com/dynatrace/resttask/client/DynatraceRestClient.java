package com.dynatrace.resttask.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DynatraceRestClient implements DynatraceClient {
    private String dataEndpoint;
    private String testEndpoint;
    private RestTemplate restTemplate;

    public DynatraceRestClient(RestTemplate restTemplate, String dataEndpoint, String testEndpoint) {
        this.restTemplate = restTemplate;
        this.dataEndpoint = dataEndpoint;
        this.testEndpoint = testEndpoint;
    }

    @Override
    public boolean isAvailable() {
        return restTemplate.getForEntity(testEndpoint, String.class)
                .getStatusCode()
                .equals(HttpStatus.OK);
    }

    @Override
    public DynatraceRestResponse getResponse() {
        return restTemplate.getForObject(dataEndpoint, DynatraceRestResponse.class);
    }
}

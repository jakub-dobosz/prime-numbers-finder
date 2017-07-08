package com.dynatrace.resttask;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(
        prefix = "dynatrace",
        ignoreUnknownFields = false
)
class RestTaskProperties {
    private String login;
    private String pass;
    private String testEndpoint;
    private String dataEndpoint;
}

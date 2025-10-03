package com.duydev.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@ConfigurationProperties(prefix = "twilio")
@Getter
@Setter
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String phoneNumber;

}

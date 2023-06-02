package com.sktelecom.authentication.passkey.passkeyrpsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class PasskeyRpSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasskeyRpSampleApplication.class, args);
    }

}

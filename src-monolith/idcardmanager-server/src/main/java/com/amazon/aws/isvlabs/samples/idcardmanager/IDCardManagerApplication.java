package com.amazon.aws.isvlabs.samples.idcardmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.amazon.aws.isvlabs.samples.idcardmanager.config.IDCardManagerProperties;

@SpringBootApplication
@EnableConfigurationProperties(IDCardManagerProperties.class)
public class IDCardManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IDCardManagerApplication.class, args);
    }
}


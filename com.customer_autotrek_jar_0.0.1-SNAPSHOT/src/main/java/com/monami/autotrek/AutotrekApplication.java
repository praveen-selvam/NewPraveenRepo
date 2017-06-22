package com.monami.autotrek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan("com.customer.autotrek")
public class AutotrekApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutotrekApplication.class, args);
    }

}

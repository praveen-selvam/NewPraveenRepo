package com.autotrek.instantauto.config;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the timezone.
 *
 * @author Joe C. McPherson
 */
@Configuration
public class TimezoneConfig {

    @PostConstruct
    public void initializeTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}

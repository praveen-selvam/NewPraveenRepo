package com.autotrek.instantauto.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration for the model mapping library.
 *
 * @author Joe C. McPherson
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        return modelMapper;
    }
}

package com.sportnet.platform.u202216636.si729pc2u202216636.shared.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Model mapper config class
 * Provides a bean to map DTOs to entities and vice versa
 * @author Diego Castro
 * @version 1.0, 2023-11-08
 */
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

package com.mr.onlineshopping.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationConfig {

    // Configurazione del Model Mapper (per i DTO)
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}


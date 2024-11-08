package com.andrew.messenger.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public String bucket(){
        return "E:\\Idea-Projects\\messenger\\img";
    }
}

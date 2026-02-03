package com.andrew.messenger.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public String bucket(){
        return "E:\\Idea-Projects\\messenger\\img\\avatars";
    }


}

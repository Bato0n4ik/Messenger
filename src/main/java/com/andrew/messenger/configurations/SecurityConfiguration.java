package com.andrew.messenger.configurations;

import com.andrew.messenger.database.entity.Role;
import com.andrew.messenger.http.handlers.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(request ->
                    request.requestMatchers( "/login", "/users/registration", "/users", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                            .requestMatchers("/users/{id}/delete").hasAuthority(Role.ADMIN.getAuthority())
                            .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                                .successHandler(customAuthenticationSuccessHandler)
                        //.successForwardUrl("/users/redirect")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")//default behavior
                        .deleteCookies("JSESSIONID")//default behavior
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.andrew.messenger.http.handlers;

import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.database.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        Long userId = null;
        if(principal instanceof UserDetails) {
            String userName = ((UserDetails) principal).getUsername();
            userId = userRepository.findByUsername(userName).getId();
        }

        String redirectUrl = "/users/" + userId;

        response.sendRedirect(request.getContextPath() + redirectUrl);

    }
}

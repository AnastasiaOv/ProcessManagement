package org.mephi.processmanagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Анастасия on 13.11.2017.
 * Класс для реализации возможности переходов на страницы админа/пользователя
 */
public class CustomSuccessAutentication implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        for (GrantedAuthority auth:authentication.getAuthorities()){
            if (auth.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("admin");
            } else
                response.sendRedirect("user");
        }

    }
}

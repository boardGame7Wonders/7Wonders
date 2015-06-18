package com.boardgame.sevenwonders.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Returns a error code to the client, when Ajax request fails.
 */
@Slf4j
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    private final int code;

    public AjaxAuthenticationEntryPoint(int code) {
        this.code = code;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException,
            ServletException {
        log.error("Request failure", authenticationException);
        response.sendError(code);
    }
}

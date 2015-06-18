package com.boardgame.sevenwonders.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Returns a error code to the client, when Ajax authentication fails.
 */
@Slf4j
public class AjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final int code;

    public AjaxAuthenticationFailureHandler(int code) {
        this.code = code;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
            throws IOException, ServletException {
        log.error("Authentication failure", authenticationException);
        response.sendError(code);
    }
}

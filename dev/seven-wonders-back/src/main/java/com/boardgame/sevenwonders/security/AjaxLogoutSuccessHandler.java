package com.boardgame.sevenwonders.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import com.boardgame.sevenwonders.service.PlayerService;

/**
 * Spring Security success handler, specialized for Ajax requests.
 */
@Service
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Resource
	private PlayerService playerService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	String login = SecurityUtils.getLoginFromAuthentication(authentication);
    	playerService.removePlayer(login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

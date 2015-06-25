/**
 * Company: SWORD Copyright (c) 2014
 */
package com.boardgame.sevenwonders.security;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.boardgame.sevenwonders.model.Player;
import com.boardgame.sevenwonders.service.PlayerService;

/**
 * Authentication Provider. <br/>
 *
 */
@Slf4j
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Resource
	PlayerService playerService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (null == authentication.getPrincipal()) {
			throw new BadCredentialsException("No user principal supplied");
		}

		String login = SecurityUtils.getLoginFromAuthentication(authentication);

		if (StringUtils.isEmpty(login)) {
			throw new BadCredentialsException("No login supplied");
		}
		
		Player player = playerService.findByLogin(login);
		
		if (player == null) {
			playerService.newPlayer(login);
		} else {
			throw new BadCredentialsException("Player already exists.");
		}

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), null);

		return result;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication) || PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

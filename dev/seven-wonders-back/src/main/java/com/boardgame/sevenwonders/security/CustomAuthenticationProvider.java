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

import com.boardgame.sevenwonders.model.GameConstant;
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
		String login = SecurityUtils.getLoginFromAuthentication(authentication);
		log.debug("Try logging in with player {}", login);

		if (StringUtils.isBlank(login)) {
			throw new BadCredentialsException("login.error.empty");
		}
		
		if (playerService.countAll() == GameConstant.MAX_PLAYERS) {
			throw new BadCredentialsException("login.error.maxPlayers");
		}
		
		Player player = playerService.findByLogin(login);
		
		if (player == null) {
			playerService.newPlayer(login);
		} else {
			throw new BadCredentialsException("login.error.playerExists");
		}

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), null);

		return result;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication) || PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

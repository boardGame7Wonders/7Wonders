package com.boardgame.sevenwonders.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.sevenwonders.model.Player;
import com.boardgame.sevenwonders.model.PlayersDetails;
import com.boardgame.sevenwonders.security.SecurityUtils;
import com.boardgame.sevenwonders.service.PlayerService;

@RestController
@RequestMapping(value = "/api/rest")
@Slf4j
public class AuthenticationController {
	
	@Resource
	PlayerService playerService;
	
	@Autowired
	private SessionRegistry sessionRegistry;

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public void isAuthenticated(HttpServletRequest request) {
		String login = SecurityUtils.getCurrentAuthenticatedLogin();
		log.debug("User login : {}", login);
		if (StringUtils.isBlank(login) || StringUtils.equals(login, SecurityUtils.ANONYMOUS_USER)) {
            throw new BadCredentialsException("login.error.unauthorized");
        }
	}
	
	@RequestMapping(value = "/playersDetails", method = RequestMethod.GET)
    public ResponseEntity<PlayersDetails> getUserDetails(HttpServletRequest request) {
        String login = SecurityUtils.getCurrentAuthenticatedLogin();
        if (null == login) {
            throw new BadCredentialsException("login.error.unauthorized");
        }
        return new ResponseEntity<PlayersDetails>(new PlayersDetails(playerService.getAll(), login), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/kickPlayer/{login}", method = RequestMethod.POST)
    public ResponseEntity<PlayersDetails> kickPlayer(HttpServletRequest request, @PathVariable String login) {
		String selfLogin = SecurityUtils.getCurrentAuthenticatedLogin();
		// check if player is authorized to kick player
		Player self = playerService.findByLogin(selfLogin);
		if (self == null || !self.isHost()) {
			return new ResponseEntity<PlayersDetails>(HttpStatus.UNAUTHORIZED);
		} else {
			try {
				playerService.kickPlayer(login);
			} catch (Exception e) {
				return new ResponseEntity<PlayersDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<PlayersDetails>(new PlayersDetails(playerService.getAll(), selfLogin), HttpStatus.OK);
		}
    }

}

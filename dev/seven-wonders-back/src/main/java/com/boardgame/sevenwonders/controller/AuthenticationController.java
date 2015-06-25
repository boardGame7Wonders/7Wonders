package com.boardgame.sevenwonders.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.sevenwonders.model.Player;
import com.boardgame.sevenwonders.security.SecurityUtils;
import com.boardgame.sevenwonders.service.PlayerService;

@RestController
@RequestMapping(value = "/api/rest")
@Slf4j
public class AuthenticationController {
	
	@Resource
	PlayerService playService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public void isAuthenticated(HttpServletRequest request) {
		String login = SecurityUtils.getCurrentAuthenticatedLogin();
		log.debug("User login : {}", login);
		if (StringUtils.isBlank(login)) {
            throw new BadCredentialsException("Not authenticated");
        }
		if (StringUtils.equals(login, SecurityUtils.ANONYMOUS_USER)) {
            throw new BadCredentialsException("Anonymous users not authorized to access this page");
        }
	}
	
	@RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    public ResponseEntity<Player> getUserDetails(HttpServletRequest request) {
        String login = SecurityUtils.getCurrentAuthenticatedLogin();
        if (null == login) {
            throw new BadCredentialsException("Not authenticated");
        }

        return new ResponseEntity<Player>(playService.findByLogin(login), HttpStatus.OK);
    }

}

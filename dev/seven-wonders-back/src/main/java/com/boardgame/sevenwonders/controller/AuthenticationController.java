package com.boardgame.sevenwonders.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.sevenwonders.security.SecurityUtils;

@RestController
@RequestMapping(value = "/api/rest")
@Slf4j
public class AuthenticationController {

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public void isAuthenticated(HttpServletRequest request) {
		String login = SecurityUtils.getCurrentAuthenticatedLogin();
	}

}

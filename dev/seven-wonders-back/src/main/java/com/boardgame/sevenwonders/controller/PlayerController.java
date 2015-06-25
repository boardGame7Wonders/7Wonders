package com.boardgame.sevenwonders.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Player;
import com.boardgame.sevenwonders.service.PlayerService;

@RestController
@RequestMapping(value = "/api/rest/player")
public class PlayerController {

	@Resource
	PlayerService playerService;

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public ResponseEntity<Player> getPlayer(@PathVariable String login) {
		try {
			Player player = playerService.findByLogin(login);
			if (null == player) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Player>(player, HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{login}/play", method = RequestMethod.POST)
	public ResponseEntity<Player> playCard(@PathVariable String login,
			@RequestBody Card card) {
		try {
			Player player = playerService.playCard(login, card);
			if (null == player) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Player>(player, HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

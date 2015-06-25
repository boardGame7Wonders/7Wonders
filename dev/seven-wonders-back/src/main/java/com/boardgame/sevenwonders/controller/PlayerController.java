package com.boardgame.sevenwonders.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Player;
import com.boardgame.sevenwonders.service.CardService;
import com.boardgame.sevenwonders.service.PlayerService;

@RestController
@RequestMapping(value = "/api/rest/player")
public class PlayerController {

	@Resource
	PlayerService playerService;
	
	@Resource
	CardService cardService;

	@RequestMapping(value = "/{playerId}", method = RequestMethod.GET)
	public ResponseEntity<Player> getPlayer(@PathVariable Integer playerId) {
		try {
			Player player = playerService.findById(playerId);
			if (null == player) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Player>(player, HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{playerId}/play", method = RequestMethod.POST)
	public ResponseEntity<Player> playCard(@PathVariable Integer playerId,
			@RequestBody Card card) {
		try {
			/*Post only id of the card is enough, e.g. { "id": 100 }*/
			Card card_complete = cardService.getCardByID(card.getId());
			Player player = playerService.playCard(playerId, card_complete);
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

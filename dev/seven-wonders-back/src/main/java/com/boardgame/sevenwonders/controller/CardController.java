package com.boardgame.sevenwonders.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.sevenwonders.model.Card;

@RestController
@RequestMapping(value = "/api/rest/card")
public class CardController {
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Card>> getAll() {
		try {
			// should be in a service, to do later
			List<Card> cardList = new ArrayList<>();
			cardList.add(new Card(0, "Lumber Yard", 1));
			cardList.add(new Card(1, "Glassworks", 2));
			cardList.add(new Card(2, "Arsenal", 3));
			
			return new ResponseEntity<List<Card>>(cardList, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

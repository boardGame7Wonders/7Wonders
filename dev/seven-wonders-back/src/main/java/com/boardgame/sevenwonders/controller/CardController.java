package com.boardgame.sevenwonders.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.service.CardService;
import com.boardgame.sevenwonders.service.CardServiceImpl;


@RestController
@RequestMapping(value = "/api/rest/card")
public class CardController {
	
	@Resource
	CardService cardService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Card>> getAll() {
		try {
			// should be in a service, to do later
			List<Card> cardList = cardService.getAllCards() ;
			
			return new ResponseEntity<List<Card>>(cardList, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

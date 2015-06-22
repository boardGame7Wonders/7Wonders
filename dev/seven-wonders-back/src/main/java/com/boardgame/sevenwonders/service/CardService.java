package com.boardgame.sevenwonders.service;

import com.boardgame.sevenwonders.model.Card;

import java.util.List;

public interface CardService {
	public List<Card> getAllCards();
	
	public List<Card> getCardsForNPlayer(int nbPlayner);
	
}

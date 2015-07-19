package com.boardgame.sevenwonders.service;

import java.util.Map;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Player;

public interface PlayerService {
	
	Player findById(int playerId);
	
	Player playCard(int playerId, Card card);
	
	Map<String, Integer> calculateScore(int playerId);

}

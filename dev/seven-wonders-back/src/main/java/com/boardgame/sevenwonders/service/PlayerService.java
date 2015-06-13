package com.boardgame.sevenwonders.service;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Player;

public interface PlayerService {
	
	Player findById(int playerId);
	
	Player playCard(int playerId, Card card);

}

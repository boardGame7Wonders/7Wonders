package com.boardgame.sevenwonders.service;

import java.util.List;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Player;

public interface PlayerService {
	
	Player findByLogin(String login);
	
	void newPlayer(String login);
	
	Player playCard(String login, Card card);
	
	void removePlayer(String login);
	
	List<Player> getAll();
	
	void kickPlayer(String login);
	
	int countAll();

}

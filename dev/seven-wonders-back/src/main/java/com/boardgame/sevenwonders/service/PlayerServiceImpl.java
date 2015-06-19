package com.boardgame.sevenwonders.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Player;

@Service
public class PlayerServiceImpl implements PlayerService, InitializingBean {

	private List<Player> players;

	@Override
	public void afterPropertiesSet() throws Exception {
		// init players
		players = new ArrayList<>();
		Player player1 = new Player(0, "Player A", new ArrayList<String>());
		player1.getResources().add("S");
		players.add(player1);
		Player player2 = new Player(1, "Player B", new ArrayList<String>());
		player2.getResources().add("W");
		players.add(player2);
	}

	@Override
	public Player findById(int playerId) {
		for (Player player : players) {
			if (player.getId() == playerId) {
				return player;
			}
		}
		return null;
	}

	@Override
	public Player playCard(int playerId, Card card) {
		Player player = findById(playerId);

		if (null != player) {
			// apply rules, not implemented
			player.getResources().add("W");
		}

		return player;
	}

}
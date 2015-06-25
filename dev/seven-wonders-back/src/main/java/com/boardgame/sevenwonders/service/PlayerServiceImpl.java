package com.boardgame.sevenwonders.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Player;

@Service
public class PlayerServiceImpl implements PlayerService, InitializingBean {

	private List<Player> players = new ArrayList<Player>();

	@Override
	public void afterPropertiesSet() throws Exception {
		// init players
		Player player1 = new Player("p1", "Player A", new ArrayList<String>());
		player1.getResources().add("S");
		players.add(player1);
		Player player2 = new Player("p2", "Player B", new ArrayList<String>());
		player2.getResources().add("W");
		players.add(player2);
	}

	@Override
	public Player findByLogin(String login) {
		for (Player player : players) {
			if (StringUtils.equals(player.getLogin(), login)) {
				return player;
			}
		}
		return null;
	}

	@Override
	public void newPlayer(String login) {
		players.add(new Player(login));
	}

	@Override
	public Player playCard(String login, Card card) {
		Player player = findByLogin(login);

		if (null != player) {
			// apply rules, not implemented
			player.getResources().add("W");
		}

		return player;
	}

}

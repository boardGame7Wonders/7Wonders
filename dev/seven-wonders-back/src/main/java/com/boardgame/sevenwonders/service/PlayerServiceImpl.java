package com.boardgame.sevenwonders.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Player;

@Service
public class PlayerServiceImpl implements PlayerService, InitializingBean {

	@Resource
	private SessionRegistry sessionRegistry;

	private List<Player> players = new ArrayList<Player>();

	@Override
	public void afterPropertiesSet() throws Exception {
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
		players.add(new Player(login, players.isEmpty()));
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

	@Override
	public void removePlayer(String login) {
		Player player = findByLogin(login);

		if (null != player) {
			players.remove(player);
			// if the player removed was host, then set a new host
			if (player.isHost() && !players.isEmpty()) {
				players.get(0).setHost(true);
			}
		}

	}

	@Override
	public List<Player> getAll() {
		return players;
	}

	@Override
	public void kickPlayer(String login) {
		List<SessionInformation> userSessions = sessionRegistry.getAllSessions(login, false);
		if (null != userSessions && !userSessions.isEmpty()) {
			for (SessionInformation session : userSessions) {
				session.expireNow();
			}
		}
		removePlayer(login);
	}
	
	@Override
	public int countAll() {
		return players.size();
	}

}

package com.boardgame.sevenwonders.model;

import java.util.List;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

@Data
public class PlayersDetails {
	
	private Player self;
	
	private List<Player> players;

	public PlayersDetails(List<Player> players, String selfLogin) {
		super();
		this.players = players;
		for (Player player : players) {
			if (StringUtils.equals(player.getLogin(), selfLogin)) {
				this.self = player;
				break;
			}
		}
	}
	
}

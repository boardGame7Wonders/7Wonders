package com.boardgame.sevenwonders.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
	
	private String login;
	
	private String name;
	
	private List<String> resources;
	
	private boolean host = false;

	public Player(String login) {
		super();
		this.login = login;
	}
	
	public Player(String login, boolean host) {
		super();
		this.login = login;
		this.host = host;
	}
	
}

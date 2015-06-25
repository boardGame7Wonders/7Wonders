package com.boardgame.sevenwonders.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
	
	private int id;
	
	private String name;
	
	private List<String> resources;
	
	private int gold;
	
	private int militaryMight;

}

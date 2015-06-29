package com.boardgame.sevenwonders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boardgame.sevenwonders.model.Card.ScienceCategory;

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
	
	private int victoryPoints;
	
	private Map<ScienceCategory, Integer> sciencePoints;

}

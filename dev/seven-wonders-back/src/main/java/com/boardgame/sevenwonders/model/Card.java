package com.boardgame.sevenwonders.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	public enum CardCategory{
		COMMERCE,
		CULTURE,
		GUILD,
		MILITARY,
		PRIMARY_RESOURCE,
		SECONDARY_RESOURCE,
		SCIENCE,
		WONDER
	};
	
	public enum ScienceCategory{
		TABLET,
		RULE,
		GEAR
	};
	
	private int id;
	
	private String name;
	
	private int age;
	
	private CardCategory category;
	
	private List<String> resourceCost;
	
	private int goldCost;
	
	private List<CardEffect> effects;
	
	private int upgradeFromCardID;

}

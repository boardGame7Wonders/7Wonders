package com.boardgame.sevenwonders.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEffect {
	
	public enum EffectType{
		IMMEDIATE,
		ENDGAME
	};
	private EffectType type;
	
	private String name;
	
	private List<String> parameters;
	
}

package com.boardgame.sevenwonders.service;

import java.util.List;
import java.util.Map;

import com.boardgame.sevenwonders.model.Card.ScienceCategory;
import com.boardgame.sevenwonders.model.CardEffect;
import com.boardgame.sevenwonders.model.Constants;
import com.boardgame.sevenwonders.model.Player;

public class EffectMechanism {

	protected static Player playerGainGold(Player player, int goldAmount) {
		player.setGold(player.getGold() + goldAmount);
		return player;
	}

	protected static Player playerProduceResource(Player player,
			List<String> resourceList) {
		for (String resource : resourceList) {
			player.getResources().add(resource);
			player.getTradableResources().add(resource);
		}
		return player;
	}
	
	protected static Player playerImportResource(Player player,
			List<String> resourceList) {
		for (String resource : resourceList) {
			player.getResources().add(resource);
		}
		return player;
	}

	protected static Player playerModifyMilitaryMight(Player player,
			String operator_s, int value) {
		switch (operator_s) {
		case Constants.OPERATOR_MINUS:
			player.setMilitaryMight(player.getMilitaryMight() - value);
			break;
		case Constants.OPERATOR_PLUS:
			player.setMilitaryMight(player.getMilitaryMight() + value);
			break;
		}

		return player;
	}
	
	protected static Player playerModifyScience(Player player, String scienceType, String operator_s, int value){
		ScienceCategory scienceCategory = null;
		switch (scienceType){
		case Constants.SCIENCE_GEAR:
			scienceCategory = ScienceCategory.GEAR;
			break;
		case Constants.SCIENCE_RULE:
			scienceCategory = ScienceCategory.RULE;
			break;
		case Constants.SCIENCE_TABLET:
			scienceCategory = ScienceCategory.TABLET;
			break;
		default:
			/* TODO: make this an error*/
			break;
		}
		
		Map<ScienceCategory, Integer> scienceMap = player.getSciencePoints();
		
		switch (operator_s) {
		case Constants.OPERATOR_MINUS:
			scienceMap.put(scienceCategory, scienceMap.get(scienceCategory) - value);
			break;
		case Constants.OPERATOR_PLUS:
			scienceMap.put(scienceCategory, scienceMap.get(scienceCategory) + value);
			break;
		}
		
		return player;
	}

	protected static Player playerGainVictoryPoints(Player player,
			int victoryPoints) {
		player.setVictoryPoints(player.getVictoryPoints() + victoryPoints);
		return player;
	}

	/* Only this function should be called from exterior */
	public static Player applyEffectToPlayer(Player player,
			CardEffect cardEffect) {
		switch (cardEffect.getName()) {
		case Constants.EFFECT_GAIN_GOLD: {
			String s_goldAmount = cardEffect.getParameters().get(0);
			int goldAmount = Integer.valueOf(s_goldAmount);
			return playerGainGold(player, goldAmount);
		}
		case Constants.EFFECT_PRODUCE_RESOURCE: {
			return playerProduceResource(player, cardEffect.getParameters());
		}
		case Constants.EFFECT_IMPORT_RESOURCE: {
			return playerImportResource(player, cardEffect.getParameters());
		}
		case Constants.EFFECT_MILITARY_MIGHT_MODIFIER: {
			String operator_s = cardEffect.getParameters().get(0);
			int value = Integer.valueOf(cardEffect.getParameters().get(1));
			return playerModifyMilitaryMight(player, operator_s, value);
		}
		case Constants.EFFECT_GAIN_VICTORY_POINTS: {
			int value = Integer.valueOf(cardEffect.getParameters().get(0));
			return playerGainVictoryPoints(player, value);
		}
		case Constants.EFFECT_SCIENCE_MODIFIER:{
			String scienceType = cardEffect.getParameters().get(0);
			String operator_s = cardEffect.getParameters().get(1);
			int value = Integer.valueOf(cardEffect.getParameters().get(2));
			return playerModifyScience(player, scienceType, operator_s, value);
		}
		default:
			break;
		}
		return null;
	}
}

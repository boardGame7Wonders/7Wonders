package com.boardgame.sevenwonders.service;

import java.util.List;

import com.boardgame.sevenwonders.model.CardEffect;
import com.boardgame.sevenwonders.model.Constants;
import com.boardgame.sevenwonders.model.Player;

public class EffectMechanism {
	
	protected static Player playerGainGold(Player player, int goldAmount){
		player.setGold(player.getGold() + goldAmount);
		return player;
	}
	
	protected static Player playerProduceResource(Player player, List<String> resourceList){
		for(String resource: resourceList){
			player.getResources().add(resource);
		}
		return player;
	}
	
	protected static Player playerModifyMilitaryMight(Player player, String operator_s, int value){
		switch(operator_s){
		case Constants.OPERATOR_MINUS:
			player.setMilitaryMight(player.getMilitaryMight() - value);
			break;
		case Constants.OPERATOR_PLUS:
			player.setMilitaryMight(player.getMilitaryMight() + value);
			break;
		}
		
		return player;
	}
	
	/*Only this function should be called from exterior*/
	public static Player applyEffectToPlayer(Player player, CardEffect cardEffect) {
		switch(cardEffect.getName()){
			case Constants.EFFECT_GAIN_GOLD:
				String s_goldAmount = cardEffect.getParameters().get(0);
				int goldAmount = Integer.valueOf(s_goldAmount);
				playerGainGold(player, goldAmount);
				break;
			case Constants.EFFECT_PRODUCE_RESOURCE:
				playerProduceResource(player, cardEffect.getParameters());
				break;
			case Constants.EFFECT_MILITARY_MIGHT_MODIFIER:
				String operator_s = cardEffect.getParameters().get(0);
				int value = Integer.valueOf(cardEffect.getParameters().get(1));
				playerModifyMilitaryMight(player,operator_s,value);
				break;
			default:
				break;
		}
		return null;
	}
}

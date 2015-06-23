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
			default:
				break;
		}
		return null;
	}
}

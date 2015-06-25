package com.boardgame.sevenwonders.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.CardEffect;
import com.boardgame.sevenwonders.model.Player;

@Service
public class PlayerServiceImpl implements PlayerService, InitializingBean {

	private List<Player> players;

	protected boolean cardCost(Player player, Card card, boolean apply){
		if(player.getGold() >= card.getGoldCost()){
			List<String> playerResource = new LinkedList<String>(player.getResources());
			List<String> cardResource = card.getResourceCost();
			for(String res: cardResource){
				boolean hasRes = false;
				for (Iterator<String> iterator = playerResource.iterator(); iterator
						.hasNext();) {
					String cursorRes = iterator.next();
					if(cursorRes.contains(res)){
						hasRes = true;
						if(apply){
							playerResource.remove(cursorRes);
						}
						break;
					}
				}
				if(!hasRes){
					// has not enough resource
					return false;
				}
			}
			if(apply){
				player.setGold(player.getGold() - card.getGoldCost());
			}
		}else{
			//has not enough gold
			return false;
		}
		
		return true;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// init players
		players = new ArrayList<>();
		Player player1 = new Player(0, "Player A", new ArrayList<String>(), 1, 0, 0);
		players.add(player1);
		Player player2 = new Player(1, "Player B", new ArrayList<String>(), 3, 0, 0);
		players.add(player2);
	}

	@Override
	public Player findById(int playerId) {
		for (Player player : players) {
			if (player.getId() == playerId) {
				return player;
			}
		}
		return null;
	}

	@Override
	public Player playCard(int playerId, Card card) {
		Player player = findById(playerId);

		if (null != player) {
			if(cardCost(player, card, true)){
				for(CardEffect cardEffect: card.getEffects()){
					EffectMechanism.applyEffectToPlayer(player, cardEffect);
				}
			}
		}

		return player;
	}

}

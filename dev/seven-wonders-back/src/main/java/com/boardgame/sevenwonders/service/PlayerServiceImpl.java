package com.boardgame.sevenwonders.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.Card.ScienceCategory;
import com.boardgame.sevenwonders.model.CardEffect;
import com.boardgame.sevenwonders.model.Player;

@Service
public class PlayerServiceImpl implements PlayerService, InitializingBean {

	private List<Player> players;

	protected Player createPlayer(int id, String name){
		Player player = new Player(id, name, new ArrayList<String>(), 1, 0, 0, new HashMap<ScienceCategory, Integer>());
		/*initiate science score*/
		player.getSciencePoints().put(ScienceCategory.GEAR,   0);
		player.getSciencePoints().put(ScienceCategory.RULE,   0);
		player.getSciencePoints().put(ScienceCategory.TABLET, 0);
		/*Add to player pool*/
		players.add(player);
		return player;
	}
	
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
		// initialize players
		players = new ArrayList<>();
		Player player1 = createPlayer(0, "Player A"); 
		Player player2 = createPlayer(1, "Player B"); 
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

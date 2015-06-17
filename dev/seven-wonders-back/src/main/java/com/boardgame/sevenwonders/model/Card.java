package com.boardgame.sevenwonders.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    
	private int id;
	
	private String name;
        
        //Gold cost needed to construct the card
        private int goldCost;
	
        //Resssouce cost needed to construct the card
        private Resource[] resCost;
        
        //Card categroy
        private CardCategory category;
        
        //Id of the cards to which this card can be upgraded
        private int[] upgradeToId;
        
        //effect when the card is played
        //private Effect effectOnPlay;
        
        //victory points the card gives(calculted)
        //private VictoryPoints vps;
}

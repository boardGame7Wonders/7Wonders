package com.boardgame.sevenwonders.model;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
	
	private int id;
	
	private String name;
	
	private Board gameBoard;
	
	private Player neighborLeft;
	
	private Player neighborRight;
	
	private int gold;
	
	private ArrayList<Resource> resBank;
	
	private ArrayList<Resource> tradableResBank;
	
	private ArrayList<Card> handCards;
	
	private Wonder wonder;
	
	private ArrayList<Science> sciencePts;
	
	private int militaryStr;
	
	private ArrayList<Integer> militaryTokens;
	
        //To be implemented in Cities expansion
	//private int diplomateTokens;
        
        //List of cards that can be constructed from an upgrade
	private ArrayList<Integer> upgradableCards;
        
	private int basicResTradeCostLeft;
	
	private int basicResTradeCostRight;
	
	private int advResTradeCost;
	
	private HashMap<CardCategory,ArrayList<Card>> constructedCards;
	
	private boolean playLastCard;
	
	private boolean freeConstructionEachAge;
        
        private boolean validateResAllocPlan(Card iCard, Direction[] iResAllocPlan) {        
            if(iCard.getResCost().length != iResAllocPlan.length) {
                //Card resource cost and resource allocation plan should have the same size,
                //this is a 1 to 1 mapping
                return false;
            }
            
            //Todo: implement the algo
            
            return true;
        }
	
        private void constructCard(Card iCard) {
                handCards.remove(iCard);
		constructedCards.get(iCard.getCategory()).add(iCard);
                
                //mark the cards which can be constructed from upgrade later
                if(iCard.getUpgradeToId() != null) {
                    for(int cardId : iCard.getUpgradeToId()) {
                        upgradableCards.add(cardId);
                    }
                }
                
                //Todo: apply post-construction effect of the card
        }
        
	//iCard: the Card to be constructed
	//iResAllocPlan, the trade direction of each resource needed to construct iCard
	public boolean playCard(Card iCard, Direction[] iResAllocPlan) {
		if(!constructedCards.get(iCard.getCategory()).contains(iCard)) {
			//Can not construct twice the same card
			return false;
		}
		
		//check if the resource allocation plan is valid
		if(!validateResAllocPlan(iCard, iResAllocPlan)) {
			//Not enough resource, can not construct the card
			return false;
		}
		
		//Everything is OK, construct the card!
		constructCard(iCard);
		
		return true;
	}
        
        public boolean upgradeCard(Card iCard) {
            	if(!constructedCards.get(iCard.getCategory()).contains(iCard)) {
			//Can not construct twice the same card
			return false;
		}
                
                if(!upgradableCards.contains(iCard.getId())) {                  	
                        return false;
                }
                
                constructCard(iCard);
                return true;
        }
	
	public void sellCard(Card iCard) {
		handCards.remove(iCard);
		gameBoard.getDiscardedCards().add(iCard);
		gold = gold + 3;
	}
	
	public boolean upgradeWonder(Card iCard, Direction[] iResAllocPlan) {
		if(wonder.getWonderStages().isEmpty()) {
			//no more wonder stage available to construct
			return false;
		}
		
		//get the next available wonder stage
		Card wonderCard = wonder.getWonderStages().get(0);
		
		//check if the resource allocation plan is valid
		if(!validateResAllocPlan(wonderCard, iResAllocPlan)) {
			//Not enough resource, can not construct the card
			return false;
		}
                
		constructCard(wonderCard);   
                return true;
	}
        
        private void fightWith(Player iPlayer) {
            if(militaryStr > iPlayer.getMilitaryStr()) {
                switch(gameBoard.getCurrentAge()){
                    case 1 : militaryTokens.add(1);
                             break;
                    case 2 : militaryTokens.add(3);
                             break;
                    case 3 : militaryTokens.add(5);
                             break;
                }
            }
            else if(militaryStr < iPlayer.getMilitaryStr()) {
                militaryTokens.add(-1);
            }
        }
        
        public void conflict() {
            //Todo check if neighbors have diplomate tokens for Cities expansion
            fightWith(neighborLeft);
            fightWith(neighborRight);
        }
}

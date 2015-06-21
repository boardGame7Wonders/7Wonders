package com.boardgame.sevenwonders.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor

/**
 *
 * @author Lichen
 */
public class ElemCounter {
	
	public static int CountCards(Player iPlayer, Direction iCountOnDirection, CardCategory[] iCountOnCategories) {
		
		int cardNum = 0;
		
		for(CardCategory countOnCategory:iCountOnCategories) {
			
			switch(iCountOnDirection) {
				case SELF:		cardNum += iPlayer.getConstructedCards().get(countOnCategory).size();
								break;
				case NEIGHBORS: cardNum += iPlayer.getNeighborLeft().getConstructedCards().get(countOnCategory).size() +
										  iPlayer.getNeighborRight().getConstructedCards().get(countOnCategory).size();
								break;
				case ALL: 		cardNum += iPlayer.getConstructedCards().get(countOnCategory).size() +
										  iPlayer.getNeighborLeft().getConstructedCards().get(countOnCategory).size() +
										  iPlayer.getNeighborRight().getConstructedCards().get(countOnCategory).size();
								break;
				case LEFT:
				case RIGHT:		//LEFT and RIGHT are not accepted as Direction here! An error or an exception should be returned
				default:	break;
			}
			
		}
		
		return cardNum;
	}
	
	public static int CountMilitaryTokens(Player iPlayer, Direction iCountOnDirection, boolean isWin) {
		
		int tokenNum = 0;
		
		//If isWin, count all winning(positive value) tokens, else count all loosing(negative value) tokens
		int calCoeff = isWin ? 1 : -1;
		
		switch(iCountOnDirection) {
			case SELF: 		for(int tokenValue : iPlayer.getMilitaryTokens()) {
								tokenNum += (((tokenValue*calCoeff) > 0) ? 1 : 0);
							}
							break;
			case NEIGHBORS: for(int tokenValue : iPlayer.getNeighborLeft().getMilitaryTokens()) {
								tokenNum += (((tokenValue*calCoeff) > 0) ? 1 : 0);
							}
							for(int tokenValue : iPlayer.getNeighborRight().getMilitaryTokens()) {
								tokenNum += (((tokenValue*calCoeff) > 0) ? 1 : 0); 
							}
							break;
			case LEFT:
			case RIGHT:
			case ALL:		//Not accepted!
			default:	break;
		}
		
		return tokenNum;
	}
}

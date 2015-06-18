package com.boardgame.sevenwonders.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor

/**
 *
 * @author Lichen
 */
public class ElemCounter {
	
	private CountType type;
	
	private CardCategory countCategory;
	
	private Direction countDirection;
	
	public int getResult(Player iPlayer) {
		switch(type) {
			case CountType.COUNTCARD:       return countCards(iPlayer);
			case CountType.COUNTWINTOKEN:   return countMilitaryWinTokens(iPlayer);
			case CountType.COUNTLOOSETOKEN: return countMilitaryLooseTokens(Player iPlayer);
			case default:                   return 0;
		}
	}
	
	private int countCards(Player iPlayer) {
		
		int cardNum = 0;
		
		switch(countDirection) {
			case SELF:		cardNum = iPlayer.getConstructedCards().get(countCategory).size();
							break;
			case NEIGHBORS: cardNum = iPlayer.getNeighborLeft().getConstructedCards().get(countCategory).size() +
									  iPlayer.getNeighborRight().getConstructedCards().get(countCategory).size();
							break;
			case ALL: 		cardNum = iPlayer.getConstructedCards().get(countCategory).size() +
							          iPlayer.getNeighborLeft().getConstructedCards().get(countCategory).size() +
							          iPlayer.getNeighborRight().getConstructedCards().get(countCategory).size();
							break;
			case LEFT:
			case RIGHT:		//LEFT and RIGHT are not accepted as Direction here! An error or an exception should be returned
			case default:	break;
		}
		
		return cardNum;
	}
	
	private int countMilitaryWinTokens(Player iPlayer) {
		return countMilitaryTokens(iPlayer, true);
	}
	
	private int countMilitaryLooseTokens(Player iPlayer) {
		return countMilitaryTokens(iPlayer, false);
	}
	
	private int countMilitaryTokens(Player iPlayer, boolean isWin) {
		
		int tokenNum = 0;
		
		//If isWin, count all winning(positive value) tokens, else count all loosing(negative value) tokens
		int calCoeff = isWin ? 1 : -1;
		
		switch(countDirection) {
			case SELF: 		for(int tokenValue : iPlayer.getMilitaryTokens()) {
								tokenNum += (((tokenValue*calCoeff) > 0) ? 1 : 0) 
							}
							break;
			case NEIGHBORS: for(int tokenValue : iPlayer.getNeighborLeft().getMilitaryTokens()) {
								tokenNum += (((tokenValue*calCoeff) > 0) ? 1 : 0) 
							}
							for(int tokenValue : iPlayer.getNeighborRight().getMilitaryTokens()) {
								tokenNum += (((tokenValue*calCoeff) > 0) ? 1 : 0) 
							}
							break;
			case LEFT:
			case RIGHT:
			case ALL:		//Not accepted!
			case default:	break;
		}
		
		return tokenNum;
	}
}

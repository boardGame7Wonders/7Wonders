package com.boardgame.sevenwonders.model;

/**
 *
 * @author Lichen
 */
 public enum Effect {
	 
	 private EffectType type;
	 
	 private ElemCounter myCardCounter;
	 
	  
	 
	 private final int baseValue;
	 
	 //This is a constant effect, it doesn't rely on player's or player's neighbors' status
	 Effect(EffectType iType, int iValue) {
		 
		 type = iType;
		 baseValue = value;
		 
	 }
	 
	 //This is an effect that should be calculated based on player or player's neighbors' status
	 Effect(CountType iCountType, int iValue, CardCategory iCategory, Direction iDirection) {
		 
		 type = EffectType.MONEY; //For the moment, the only available instant effect is to get money.
		 baseValue = value;
		 myCardCounter = new ElemCounter(iCountType, iCategory, iDirection);
		 
	 }
	 
	 public void trigger(Player iPlayer) {
		 
	 }
	 
	 private enum EffectType {
		 RESOURCE,
		 MILITARY,
		 MONEY,
		 //DROPMONEY,
		 //DIPLOMATE,
		 SCIENCE		 
	 }
 }
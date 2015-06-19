package com.boardgame.sevenwonders.model;

/**
 *
 * @author Lichen
 */
 
 //To be discussed, if this should be an Enum or a Class that we instanciate from a Json file!
 public enum Effect {
	 
	 //Some example effects:
	 ADD_ONE_WOOD(EffectType.RESOURCE, Resource.W, 1), //add 1 wood to the resource bank
	 ADD_TWO_BRICK(EffectType.RESOURCE, Resource.B, 2),
	 ADD_ONE_TABLET(EffectType.SCIENCE, Science.TABLE, 1),
	 ADD_FIVE_GOLD(EffectType.MONEY, 5),
	 ADD_THREE_SHIELD(EffectType.MILITARY, 3),
	 LIGHTHOUSE_EFFECT(EffectType.MONEY, CountType.COUNTCARD, 1, [CardCategory.COMMERCIAL], Direction.SELF), //gain 1 gold for each commercial card you own
	 LIGHTHOUSE_VPS(EffectType.VICTORY, CountType.COUNTCARD, 1, [CardCategory.COMMERCIAL], Direction.SELF),
	 ADD_SIX_VPS(EffectType.VICTORY, 8),
	 BUILDERS_GUILD_VPS(EffectType.VICTORY, CountType.COUNTCARD, 1, [CardCategory.WONDER], Direction.ALL), //gain 1 vps for each wonder stage you or your neighbors have built
	 STRATEGY_BUILD_VPS(EffectType.VICTORY, CountType.COUNTLOOSETOKEN, 1, null, Direction.NEIGHBORS); //gain 1 vps for each loose military token your neighbors have
	 
	 private EffectType myEffectType;

	 private CountType myCountType;
	  
	 private CardCategory[] countOnCategories;
	 
	 private Direction countOnDirection;
	 
	 private Resource resType;
	 
	 private Science scienceType;
	 
	 private final int baseValue;
	 
	 Effect(EffectType iType, Resource iResource, int iValue) {
		 myEffectType = iType;
		 resType = iResource;
		 baseValue = iValue;
	 }
	 
	 Effect(EffectType iType, Science iScience, int iValue) {
		 myEffectType = iType;
		 scienceType = iScience;
		 baseValue = iValue;
	 }
	 
	 //This is a constant effect, it doesn't rely on player's or player's neighbors' status
	 Effect(EffectType iType, int iValue) {
		 
		 myCountType = CONSTANT;
		 myEffectType = iType;
		 baseValue = value;
		 
	 }
	 
	 //This is an effect that should be calculated based on player or player's neighbors' status
	 Effect(EffectType iType, CountType iCountType, int iValue, CardCategory[] iCategories, Direction iDirection) {
		 
		 myEffectType = iType;
		 myCountType = iCountType;
		 baseValue = value;
		 countOnCategories = iCategories;
		 countOnDirection = iDirection;
		 
	 }
	 
	 //Trigger the assigned effect, in case of victory point calculation, the result will be returned
	 public int trigger(Player iPlayer) {		 
		 int effectValue;
		 switch(myCountType) {
			 case CountType.CONSTANT:        effectValue = baseValue;
											 break;
			 case CountType.COUNTCARD:       effectValue = baseValue * ElemCounter.CountCards(iPlayer, countOnDirection, countOnCategory);
											 break;
			 case CountType.COUNTWINTOKEN:   effectValue = baseValue * ElemCounter.CountMilitaryTokens(iPlayer, countOnDirection, true);
											 break;
			 case CountType.COUNTLOOSETOKEN: effectValue = baseValue * ElemCounter.CountMilitaryTokens(iPlayer, countOnDirection, false);
											 break;
			 case default: 					 break;
		 }
		 
		 switch(myEffectType) {
			 case EffectType.RESOURCE: for(int i = 0; int < effectValue; ++i) {
											iPlayer.getResBank().add(resType);
											if(resType!=Resource.WBSO && resType!=Resource.LGP) {
												iPlayer.getTradableResBank().add(resType);
											}
									   }
									   break;
			 case EffectType.MILITARY: iPlayer.setMilitaryStr(iPlayer.getMilitaryStr()+effectValue);
									   break;
			 case MONEY:               iPlayer.setGold(iPlayer.getGold()+effectValue);
									   break;
			 case SCIENCE:			   iPlayer.getSciencePts().add(scienceType);
									   break;
			 case VICTORY:
			 case default:             break;
		 }
		 return effectValue;
	 }
	 
	 private enum EffectType {
		 RESOURCE,
		 MILITARY,
		 MONEY,
		 //DROPMONEY,
		 //DIPLOMATE,
		 SCIENCE,
		 VICTORY
	 }
	 
	 private enum CountType {
		CONSTANT,
		COUNTCARD,
		COUNTWINTOKEN,
		COUNTLOOSETOKEN
	 }
 }
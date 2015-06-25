package com.boardgame.sevenwonders.model;

public class Constants {
	/*****Resource Types*******/
	/*Primary resources*/
	public static final String RES_WOOD    = "W";
	public static final String RES_STONE   = "S";
	public static final String RES_BRICK   = "B";
	public static final String RES_METAL   = "M";
	/*Secondary resources*/
	public static final String RES_FABRIC  = "F";
	public static final String RES_GLASS   = "G";
	public static final String RES_PAPER   = "P";
	
	/***** Modifier - Operator *******/
	public static final String OPERATOR_PLUS = "plus";
	public static final String OPERATOR_MINUS = "minus";
	
	
	/*****Card Effects******/
	/*Produce resource, parameter: list of String ==> ["W","S","BM"] */
	public static final String EFFECT_PRODUCE_RESOURCE = "produce_resource";
	/*Gain gold, parameter: int ==> gold amount to grant*/
	public static final String EFFECT_GAIN_GOLD = "gain_gold";
	/*Modify military might: string, int ==> operator , value */
	public static final String EFFECT_MILITARY_MIGHT_MODIFIER = "military_might_modifier";
	/*Gain victory points immediately:  int ==> value */
	public static final String EFFECT_GAIN_VICTORY_POINTS = "gain_victory_points";
}

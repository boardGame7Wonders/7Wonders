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
	
	/*****Card Effects******/
	/*Produce resource, parameter: list of String ==> ["W","S","BM"] */
	public static final String EFFECT_PRODUCE_RESOURCE = "produce_resource";
	/*Gain gold, parameter: int ==> gold amount to grant*/
	public static final String EFFECT_GAIN_GOLD = "gain_gold";
}

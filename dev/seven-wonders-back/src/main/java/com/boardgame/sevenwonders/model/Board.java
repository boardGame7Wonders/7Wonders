/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boardgame.sevenwonders.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 *
 * @author Lichen
 */
public class Board {
    
    private Player[] playerList;
    
    private int currentAge;
    
    private ArrayList<Card> discardedCards;
}

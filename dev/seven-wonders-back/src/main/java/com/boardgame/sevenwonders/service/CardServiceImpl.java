package com.boardgame.sevenwonders.service;

import com.boardgame.sevenwonders.model.Card;
import com.boardgame.sevenwonders.model.CardCollection;
import com.boardgame.sevenwonders.model.Player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService, InitializingBean {

	private List<Card> cardList;

	@Override
	public void afterPropertiesSet() throws Exception {
		ClassPathXmlApplicationContext context;
		context = new ClassPathXmlApplicationContext("Rules.xml");
		
		CardCollection cardCol = (CardCollection) context
				.getBean("CardCollection");
		cardList = new ArrayList<Card>(cardCol.getCardList());
		
		context.close();
	}

	@Override
	public List<Card> getAllCards() {
		return cardList;
	}

	@Override
	public List<Card> getCardsForNPlayer(int nbPlayner) {
		// TODO implements card selection and shuffling
		return getAllCards();
	}
	
	@Override
	public Card getCardByID(int cardID){
		for(Card card: cardList){
			if(card.getId() == cardID){
				return card;
			}
		}
		return new Card();
	}

}

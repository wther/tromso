package com.wther.tromso.model.util;

import com.wther.tromso.model.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Helper class for {@link com.wther.tromso.model.Card} entities
 *
 * Created by Barnabas on 10/30/2014.
 */
public class CardUtil {

    private CardUtil(){
        // Utility class.
    }

    /**
     * Converts a string containing cards to array of cards
     *
     * @param handString Text of the cards, e.g. <i></i>
     * @return
     */
    public static List<Card> cardsFromString(String handString){

        try {
            List<Card> retVal = new ArrayList<Card>();
            for (int i = 0; i < handString.length(); i+=2) {
                retVal.add(new Card(handString.substring(i, i + 2)));
            }
            return retVal;
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Failed to parse " + handString, e);
        }
    }

    /**
     * Selects a subset of cards
     *
     * @param source
     * @return
     */
    public static List<Card> select(Iterable<Card> source, Selector<Card> selector){
        List<Card> retVal = new ArrayList<Card>();
        for(Card card : source){
            if(selector.isSelected(card)){
                retVal.add(card);
            }
        }
        return retVal;
    }

    /**
     * Select the subset of cards which belong to a given suit
     *
     * @param source
     * @param suit
     * @return
     */
    public static List<Card> selectSuit(Iterable<Card> source, final Card.Suit suit){
        return select(source, new Selector<Card>() {
            @Override
            public boolean isSelected(Card item) {
                return item.getSuit() == suit;
            }
        });
    }
}

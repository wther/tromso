package com.wther.tromso.model;

import org.apache.commons.collections4.map.LinkedMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Puzzle
 *
 * Created by Barnabas on 10/30/2014.
 */
public class Puzzle {

    /**
     * Puzzle's title
     */
    private String title;

    /**
     * Contract level, 1..7
     */
    private int contractLevel;

    /**
     * Cards by player
     */
    public Map<Player, List<Card>> seats;

    /**
     * Which cards were played and by which player
     */
    private LinkedMap<Card, Player> playedCards;

    /**
     * Which cards were played and by which player this round
     */
    private LinkedMap<Player, Card> cardsOnPlay;

    /**
     * Event handler for card being played
     */
    private CardPlayedListener cardPlayedListener = null;

    public Puzzle(String title, int contractLevel, Map<Player, List<Card>> seats, Card lead) {
        this.title = title;
        this.contractLevel = contractLevel;
        this.seats = Collections.unmodifiableMap(seats);
        this.playedCards = new LinkedMap<Card, Player>();

        // Lead is by east
        this.cardsOnPlay = new LinkedMap<Player, Card>();
        playCard(Player.EAST, lead);
    }

    public String getTitle() {
        return title;
    }

    public int getContractLevel() {
        return contractLevel;
    }

    public Map<Player, List<Card>> getSeats() {
        return seats;
    }

    public List<Card> getHandFor(Player player){
        return seats.get(player);
    }

    public LinkedMap<Player, Card> getCardsOnPlay() {
        return cardsOnPlay;
    }

    public LinkedMap<Card, Player> getPlayedCards() {
        return playedCards;
    }

    public void setCardPlayedListener(CardPlayedListener cardPlayedListener) {
        this.cardPlayedListener = cardPlayedListener;
    }

    /**
     * Player a card
     */
    public void playCard(Player player, Card card){
        if(cardsOnPlay.size() == 4){
            cardsOnPlay.clear();
        }

        cardsOnPlay.put(player, card);
        playedCards.put(card, player);

        if(cardPlayedListener != null){
            cardPlayedListener.cardPlayed(player, card);
        }
    }
}

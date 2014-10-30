package com.wther.tromso.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
    private LinkedHashMap<Card, Player> playedCards;

    public Puzzle(String title, int contractLevel, Map<Player, List<Card>> seats) {
        this.title = title;
        this.contractLevel = contractLevel;
        this.seats = Collections.unmodifiableMap(seats);
        this.playedCards = new LinkedHashMap<Card, Player>();
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
}

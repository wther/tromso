package com.wther.tromso.model;

/**
 * Created by Barnabas on 11/2/2014.
 */
public interface CardPlayedListener {

    /**
     * Event handler for card being played
     *
     * @param player
     * @param card
     */
    void cardPlayed(Player player, Card card);
}

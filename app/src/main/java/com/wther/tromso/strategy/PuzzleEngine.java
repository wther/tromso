package com.wther.tromso.strategy;

import com.wther.tromso.model.Card;
import com.wther.tromso.model.Player;
import com.wther.tromso.model.Puzzle;

import java.util.List;

/**
 * Created by Barnabas on 11/2/2014.
 */
public interface PuzzleEngine {

    /**
     * Take a card played, update the puzzle and then play opponent cards until
     * a human interaction is required
     *
     * @param cardPlayed Card which is played
     *
     * @return Next player who needs to take an action, either north or south
     */
    Player playCard(String cardPlayed) throws IllegalPlayException;

    /**
     * Returns the next player
     *
     * @return
     */
    Player getNextPlayer();

    /**
     * Returns all playable cards for a player
     *
     * @param player Player for whom the playable cards are questioned
     * @return List of all the playable cards
     */
    List<Card> getPlayableCards(Player player);
}

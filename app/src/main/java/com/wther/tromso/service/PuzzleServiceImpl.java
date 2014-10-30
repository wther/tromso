package com.wther.tromso.service;

import com.wther.tromso.model.Card;
import com.wther.tromso.model.Player;
import com.wther.tromso.model.Puzzle;
import com.wther.tromso.model.util.CardUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Implementation which returns a random board
 *
 * Created by Barnabas on 10/30/2014.
 */
public class PuzzleServiceImpl implements PuzzleService {

    /**
     * {@inheritDoc}
     * @param id ID recognized as a puzzle
     * @return
     */
    @Override
    public Puzzle getPuzzle(String id) {

        Random random = new Random();
        random.setSeed(id == null ? 0 : id.hashCode());

        // Prepare deck
        StringBuilder deck = new StringBuilder();
        for(int i = 0; i < Card.SUITS.length(); i++){
            for(int j = 0; j < Card.RANKS.length(); j++){
                deck.append(Card.SUITS.charAt(i)).append(Card.RANKS.charAt(j));
            }
        }

        // Shuffle deck
        for(int i = 0; i < 200; i++){
            int a = random.nextInt(deck.length()/2)*2;
            int b = random.nextInt(deck.length()/2)*2;

            String tmp = deck.substring(a,a+2);

            deck.setCharAt(a, deck.charAt(b));
            deck.setCharAt(a+1, deck.charAt(b+1));
            deck.setCharAt(b, tmp.charAt(0));
            deck.setCharAt(b+1, tmp.charAt(1));
        }

        // Assign cards to players
        Map<Player, List<Card>> seats = new HashMap<Player, List<Card>>();

        final int charsPerPlayer = 13 * 2;
        seats.put(Player.NORTH, CardUtil.cardsFromString(deck.substring(0, charsPerPlayer)));
        seats.put(Player.EAST, CardUtil.cardsFromString(deck.substring(charsPerPlayer, charsPerPlayer*2)));
        seats.put(Player.SOUTH, CardUtil.cardsFromString(deck.substring(charsPerPlayer*2, charsPerPlayer*3)));
        seats.put(Player.WEST, CardUtil.cardsFromString(deck.substring(charsPerPlayer*3, charsPerPlayer*4)));

        // Prepare board
        return new Puzzle( "Board " + id, 3, seats);
    }
}

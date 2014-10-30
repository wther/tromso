package com.wther.tromso.service;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.wther.tromso.model.Card;
import com.wther.tromso.model.Player;
import com.wther.tromso.model.Puzzle;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Unit tests for the {@link com.wther.tromso.service.PuzzleServiceImpl} class.
 *
 * Created by Barnabas on 10/30/2014.
 */
public class PuzzleServiceImplTest extends InstrumentationTestCase {

    private PuzzleService target = new PuzzleServiceImpl();

    /**
     * Test that the constructor doesn't fail, and all 52 cards were dealt
     */
    public void testThatAllCardsAreDealt(){

        // Act
        Puzzle puzzle = target.getPuzzle("1");

        // Assert
        assertEquals("North should have 13 cards", 13, puzzle.getSeats().get(Player.NORTH).size());

        Set<Card> cards = new HashSet<Card>();
        for(Map.Entry<Player,List<Card>> seat : puzzle.getSeats().entrySet()){
            cards.addAll(seat.getValue());
        }

        assertEquals("All cards should be dealt, no cards should be dealt twice", 52, cards.size());
    }
}

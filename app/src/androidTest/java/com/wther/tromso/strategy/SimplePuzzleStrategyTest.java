package com.wther.tromso.strategy;

import android.test.InstrumentationTestCase;

import com.wther.tromso.model.Card;
import com.wther.tromso.model.Player;
import com.wther.tromso.model.Puzzle;
import com.wther.tromso.model.util.CardUtil;
import com.wther.tromso.service.PuzzleService;
import com.wther.tromso.service.PuzzleServiceImpl;

import java.util.List;

/**
 * Unit tests for the {@link SimplePuzzleEngine} class.
 *
 * Created by Barnabas on 11/2/2014.
 */
public class SimplePuzzleStrategyTest extends InstrumentationTestCase {

    /**
     * Service to generate boards
     */
    private PuzzleService service = new PuzzleServiceImpl();

    /**
     * Test that next player is north and that he can play a card
     */
    public void testThatNorthCanPlayACard() throws IllegalPlayException {

        // Arrange
        Puzzle puzzle = service.getPuzzle("1");

        final Card.Suit suit = puzzle.getCardsOnPlay().get(Player.EAST).getSuit();

        List<Card> cardWithSameSuit = CardUtil.selectSuit(puzzle.getHandFor(Player.NORTH), suit);

        String cardToPlay = cardWithSameSuit.get(0).getValue();

        PuzzleEngine target = new SimplePuzzleEngine(puzzle);

        // Act
        Player next = target.playCard(cardToPlay);

        // Arrange
        assertEquals("Next player should be SOUTH", Player.SOUTH, next);
        assertEquals("Two cards should be played", 3, puzzle.getCardsOnPlay().size());
        assertEquals("Last card should have been played by WEST", Player.WEST, puzzle.getPlayedCards().getValue(2));
        assertEquals("Last card played by NORTH should match", cardToPlay, puzzle.getPlayedCards().get(1).getValue());
    }

    /**
     * Test that when next player is north south can't play a card
     */
    public void testThatSouthCannotPlayACard() {
        // Arrange
        Puzzle puzzle = service.getPuzzle("1");
        final Card.Suit suit = puzzle.getCardsOnPlay().get(Player.EAST).getSuit();
        List<Card> cardWithSameSuit = CardUtil.selectSuit(puzzle.getHandFor(Player.SOUTH), suit);

        String cardToPlay = cardWithSameSuit.get(0).getValue();

        PuzzleEngine target = new SimplePuzzleEngine(puzzle);

        // Act
        try {
            target.playCard(cardToPlay);
            fail("Expected IllegalPlayException to be thrown");
        } catch (IllegalPlayException e){
            // OK, pass
        }
    }
}

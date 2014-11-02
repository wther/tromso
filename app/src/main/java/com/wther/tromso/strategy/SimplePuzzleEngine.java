package com.wther.tromso.strategy;

import com.wther.tromso.model.Card;
import com.wther.tromso.model.Player;
import com.wther.tromso.model.Puzzle;
import com.wther.tromso.model.util.CardUtil;
import com.wther.tromso.model.util.Selector;

import org.apache.commons.collections4.map.LinkedMap;

import java.util.List;

/**
 * Implementation without any AI whatsoever
 */
public class SimplePuzzleEngine implements PuzzleEngine {

    /**
     * Puzzle for the strategy
     */
    private final Puzzle puzzle;

    /**
     * Create new strategy for a puzzle
     * @param puzzle
     */
    public SimplePuzzleEngine(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player playCard(String cardPlayed) throws IllegalPlayException {

        // Determine next player
        Player next = getNextPlayer();

        // Does next player has the played card
        Card card = new Card(cardPlayed);
        if(!puzzle.getHandFor(next).contains(new Card(cardPlayed))){
            throw new IllegalPlayException("Expected " + next.name() + " to have " + cardPlayed + " but wasn't in hand");
        }

        // Was this card already played?
        if(puzzle.getPlayedCards().containsKey(card)){
            Player player = puzzle.getPlayedCards().get(card);
            throw new IllegalPlayException("Attempting to play " + card + " for " + next + " but was already played by " + player + " in " + puzzle.getPlayedCards());
        }

        // All is well, so let's play the card
        puzzle.playCard(next, card);

        // Who is next?
        Player newNext = getNextPlayer();

        // Is this a human player?
        if(newNext == Player.NORTH || newNext == Player.SOUTH){
            return newNext;
        }

        // So this is a robot player, then select any card
        List<Card> cards = getPlayableCards(newNext);

        // Game is finished
        if(cards.size() == 0){
            return null;
        }

        return playCard(cards.get(0).getValue());
    }

    @Override
    public List<Card> getPlayableCards(final Player player) {

        // Which cards are even possible to be played
        List<Card> candidates = puzzle.getHandFor(player);

        // Remove all already played cards
        candidates.removeAll(CardUtil.select(candidates, new Selector<Card>() {
            public boolean isSelected(Card item) {
                return puzzle.getPlayedCards().containsKey(item);
            }
        }));

        // Am I on lead?
        if(puzzle.getCardsOnPlay().size() == 4){
            return candidates;
        }

        // I'm not on lead, then who is?
        Card lead = puzzle.getCardsOnPlay().getValue(0);

        // Can I follow suit?
        List<Card> cardsInTheSuit = CardUtil.selectSuit(candidates, lead.getSuit());
        if(cardsInTheSuit.size() > 0){
            return cardsInTheSuit;
        } else {
            return candidates;
        }
    }

    /**
     * Determines who should be the next player
     *
     * @return
     */
    @Override
    public Player getNextPlayer(){

        if(puzzle.getCardsOnPlay().size() == 4){
            return getRoundWinner(puzzle.getCardsOnPlay());
        } else {
            // If round is still on player to the left is next
            return puzzle.getCardsOnPlay().lastKey().next();
        }
    }

    /**
     * Determines which player played the highest card
     *
     * @param cards
     * @return
     */
    private static Player getRoundWinner(LinkedMap<Player, Card> cards){

        if(cards.size() != 4){
            throw new IllegalStateException("Round winner can only be determined if 4 players have a played card, this isn't: " + cards);
        }

        Card lead = cards.getValue(0);
        Player leadBy = cards.get(0);

        Card highest = lead;
        Player highestBy = leadBy;

        for(int i = 1; i < cards.size(); i++){

            // If from the same suit
            if(lead.getSuit() == cards.getValue(i).getSuit()){
                if(cards.getValue(i).compareTo(highest) > 0){
                    highest = cards.getValue(i);
                    highestBy = cards.get(i);
                }
            }
        }

        return highestBy;
    }
}

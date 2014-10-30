package com.wther.tromso.model;

/**
 * Model class for a card, e.g. <i>SA</i>
 * <p/>
 * Created by Barnabas on 10/30/2014.
 */
public class Card {

    /**
     * Allowed suit values in order
     */
    public static final String SUITS = "CDHS";

    /**
     * Allowed rank values
     */
    public static final String RANKS = "23456789TJQKA";

    /**
     * Card value
     */
    private String value;

    /**
     * Suit of the card
     */
    private Suit suit;

    public Card(String value) {
        if(value == null){
            throw new IllegalArgumentException("value is null");
        }

        if (value.length() != 2) {
            throw new IllegalArgumentException("Illegal card: " + value);
        }

        this.value = value;

        // Determine suit
        this.suit = getSuit(value.charAt(0));

        // Determine rank
        if (RANKS.indexOf(value.charAt(1)) < 0) {
            throw new IllegalArgumentException("Illegal value for rank: " + value.charAt(1) + ", expected one of " + RANKS);
        }
    }

    /**
     * Convert char to {@link com.wther.tromso.model.Card.Suit}
     */
    private static Suit getSuit(char suitCode) {
        for (Suit suit : Suit.values()) {
            if (suit.getSuitCode() == suitCode) {
                return suit;
            }
        }

        throw new IllegalArgumentException("Invalid suit code: " + suitCode + " expected one of " + SUITS);
    }

    /**
     * Suit values for a card
     */
    public static enum Suit {
        CLUBS('C'), DIAMONDS('D'), HEARTS('H'), SPADES('S');

        /**
         * E.g. <i>S</i>
         */
        private char suitCode;

        Suit(char suitCode) {
            this.suitCode = suitCode;
        }

        public char getSuitCode() {
            return suitCode;
        }
    }

    /**
     * Get card's value
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the card's suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the card's rank, on of {@link #RANKS}
     */
    public char getRank() { return value.charAt(1); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        if (!value.equals(card.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

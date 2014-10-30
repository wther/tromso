package com.wther.tromso.model;

/**
 * Players in the game
 */
public enum Player {

    NORTH(0), WEST(1), SOUTH(2), EAST(3);

    /**
     * Players index
     */
    private final int index;

    /**
     * Enum's private constructor
     *
     * @param index
     */
    private Player(int index) {
        this.index = index;
    }

    /**
     * Returns the player's index
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the player on seat left
     *
     * @return
     */
    public Player next() {
        return toLeft(1);
    }

    /**
     * Returns the player on the left of a player
     *
     * @param numberOfSeats Number of seats to the left
     * @return The player at the seat
     */
    public Player toLeft(int numberOfSeats) {
        return withIndex((index + numberOfSeats) % 4);
    }

    /**
     * Returns the player identified by the given index
     *
     * @param index Value in the range of 0..3
     * @return The player at the seat, 0 being north
     */
    public static Player withIndex(int index) {
        for (Player player : Player.values()) {
            if (player.index == index) {
                return player;
            }
        }
        throw new IllegalArgumentException("Couldn't find player for index: " + index);
    }
}

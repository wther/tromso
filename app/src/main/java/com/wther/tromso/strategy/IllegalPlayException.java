package com.wther.tromso.strategy;

/**
 * Exception thrown from {@link PuzzleEngine} implementations
 *
 * Created by Barnabas on 11/2/2014.
 */
public class IllegalPlayException extends Exception {

    /**
     * Set exception message
     *
     * @param message
     */
    public IllegalPlayException(String message){
        super(message);
    }
}

package com.wther.tromso.service;

import com.wther.tromso.model.Puzzle;

/**
 * Service to access puzzles
 *
 * Created by Barnabas on 10/30/2014.
 */
public interface PuzzleService {

    /**
     * Access a puzzle by its id
     *
     * @param id ID recognized as a puzzle
     * @return The puzzle in its initial state
     */
    Puzzle getPuzzle(String id);
}

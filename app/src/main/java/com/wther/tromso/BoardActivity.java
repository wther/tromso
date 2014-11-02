package com.wther.tromso;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wther.tromso.layout.CardButton;
import com.wther.tromso.layout.HandLayout;
import com.wther.tromso.model.Card;
import com.wther.tromso.model.CardPlayedListener;
import com.wther.tromso.model.Player;
import com.wther.tromso.model.Puzzle;
import com.wther.tromso.service.PuzzleService;
import com.wther.tromso.service.PuzzleServiceImpl;
import com.wther.tromso.strategy.PuzzleEngine;
import com.wther.tromso.strategy.SimplePuzzleEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Activity for solving boards
 */
public class BoardActivity extends Activity implements CardPlayedListener {

    /**
     * Puzzle service
     */
    private PuzzleService puzzleService = new PuzzleServiceImpl();

    /**
     * Puzzle being played
     */
    private Puzzle puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        // Get puzzle
        puzzle = puzzleService.getPuzzle("1");
        puzzle.setCardPlayedListener(this);

        // Create engine which will play the cards
        PuzzleEngine engine = new SimplePuzzleEngine(puzzle);

        // Add hands for North & South
        HandLayout northTable = (HandLayout)findViewById(R.id.northTable);
        northTable.addCards(puzzle.getHandFor(Player.NORTH));
        northTable.setClickHandler(engine);

        HandLayout southTable = (HandLayout)findViewById(R.id.southTable);
        southTable.addCards(puzzle.getHandFor(Player.SOUTH));
        southTable.setClickHandler(engine);

        // Set cards on the table
        updatePlayedCards();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Takes the buttons on the grid, assigns them a card of they have one on the
     * {@link #puzzle} or hides them otherwise
     */
    private void updatePlayedCards(){
        Map<Player, Integer> resourceForPlayer = new HashMap<Player, Integer>();
        resourceForPlayer.put(Player.NORTH, R.id.northCard);
        resourceForPlayer.put(Player.EAST, R.id.eastCard);
        resourceForPlayer.put(Player.SOUTH, R.id.southCard);
        resourceForPlayer.put(Player.WEST, R.id.westCard);

        for(Map.Entry<Player,Integer> entry : resourceForPlayer.entrySet()){
            CardButton cardButton = (CardButton) findViewById(entry.getValue());
            cardButton.setPlayable(false);

            // If player has a card played
            if(puzzle.getCardsOnPlay().containsKey(entry.getKey())){
                cardButton.setCard(puzzle.getCardsOnPlay().get(entry.getKey()));
                cardButton.setVisibility(View.VISIBLE);
            } else {
                cardButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void cardPlayed(Player player, Card card) {
        updatePlayedCards();

        // Also find this card and hide it
        if(player == Player.NORTH || player == Player.SOUTH) {
            HandLayout layout = (HandLayout) findViewById(player == Player.NORTH ? R.id.northTable : R.id.southTable);
            layout.hideCard(card);
        }
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
}

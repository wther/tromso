package com.wther.tromso;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wther.tromso.layout.HandLayout;
import com.wther.tromso.model.Card;
import com.wther.tromso.model.Player;
import com.wther.tromso.model.Puzzle;
import com.wther.tromso.service.PuzzleService;
import com.wther.tromso.service.PuzzleServiceImpl;

import java.util.Arrays;


/**
 * Activity for solving boards
 */
public class BoardActivity extends Activity {

    /**
     * Puzzle service
     */
    private PuzzleService puzzleService = new PuzzleServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        // Get puzzle
        Puzzle puzzle = puzzleService.getPuzzle("1");

        // Add hands for North & South
        HandLayout northTable = (HandLayout)findViewById(R.id.northTable);
        northTable.addCards(puzzle.getHandFor(Player.NORTH));

        HandLayout southTable = (HandLayout)findViewById(R.id.southTable);
        southTable.addCards(puzzle.getHandFor(Player.SOUTH));
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
}

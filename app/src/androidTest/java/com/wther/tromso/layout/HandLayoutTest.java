package com.wther.tromso.layout;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.wther.tromso.BoardActivity;
import com.wther.tromso.R;
import com.wther.tromso.model.Card;

/**
 * Tests for the {@link com.wther.tromso.layout.HandLayout} class
 *
 * Created by Barnabas on 10/30/2014.
 */
public class HandLayoutTest extends ActivityInstrumentationTestCase2<BoardActivity> {

    private BoardActivity boardActivity;

    public HandLayoutTest() {
        super(BoardActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        boardActivity = getActivity();
    }

    /**
     * Test that North's dedicated hand table div
     */
    @SmallTest
    public void testThatSuitsAreDividedIntoRows(){

        // Arrange
        HandLayout handLayout = (HandLayout) boardActivity.findViewById(R.id.northTable);

        // Assert
        assertEquals("Hand should have four rows", 4, handLayout.getChildCount());

        Button firstButton = (Button)((TableRow)handLayout.getChildAt(0)).getChildAt(0);
        assertEquals("First row should have spades", 'S', firstButton.getText().charAt(0));
    }

    /**
     * Test that each suit is in order
     */
    @SmallTest
    public void testThatEachSuitIsInOrder(){

        // Arrange
        HandLayout handLayout = (HandLayout) boardActivity.findViewById(R.id.northTable);

        // Assert
        for(int i = 0; i < handLayout.getChildCount(); i++){
            TableRow suit = (TableRow) handLayout.getChildAt(i);

            int currentHighestRank = Card.RANKS.length() + 1;
            char currentHighest = 'X';
            for(int j = 0; j < suit.getChildCount(); j++){
                Button card = (Button) suit.getChildAt(j);
                char rank = card.getText().charAt(1);

                assertTrue("Expected card smaller than " + currentHighest + " but found " + rank + " in " + card.getText(),
                            currentHighestRank > Card.RANKS.indexOf(rank));

                currentHighestRank = Card.RANKS.indexOf(rank);
                currentHighest = rank;
            }
        }
    }
}

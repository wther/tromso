package com.wther.tromso;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.TableRow;

import com.wther.tromso.layout.CardButton;
import com.wther.tromso.model.Puzzle;
import com.wther.tromso.layout.HandLayout;

/**
 * Tests involving the {@link com.wther.tromso.BoardActivity}
 *
 * Created by Barnabas on 11/2/2014.
 */
public class BoardActivityTest extends ActivityInstrumentationTestCase2<BoardActivity> {

    private BoardActivity boardActivity;

    public BoardActivityTest() {
        super(BoardActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        boardActivity = getActivity();
    }

    /**
     * Test that when clicking a card is shown on the board
     */
    @UiThreadTest
    public void testThatPlayedCardIsAddedToBoard(){

        // Arrange
        HandLayout layout = (HandLayout)boardActivity.findViewById(R.id.northTable);
        TableRow row = (TableRow)layout.getChildAt(0);
        CardButton cardButton = (CardButton) row.getChildAt(0);

        Puzzle puzzle = getActivity().getPuzzle();

        // Act
        cardButton.performClick();

        // Assert
        assertEquals("North and West should've played cards", 3, puzzle.getCardsOnPlay().size());

        CardButton button = (CardButton)boardActivity.findViewById(R.id.westCard);
        assertEquals("West's played card should be on the board", puzzle.getCardsOnPlay().getValue(2).getValue(), button.getText());
        assertEquals("West's card on the board should be visible", View.VISIBLE, button.getVisibility());
    }

    /**
     * Test that when clicking a card is shown on the board
     */
    @UiThreadTest
    public void testThatCardPlayedIsRemovedFromHand(){

        // Arrange
        HandLayout layout = (HandLayout)boardActivity.findViewById(R.id.northTable);
        TableRow row = (TableRow)layout.getChildAt(0);
        CardButton cardButton = (CardButton) row.getChildAt(0);

        Puzzle puzzle = getActivity().getPuzzle();

        // Act
        cardButton.performClick();

        // Assert
        assertEquals("Card played should be hidden", View.INVISIBLE, cardButton.getVisibility());
    }
}

package com.wther.tromso.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.wther.tromso.model.Card;
import com.wther.tromso.model.util.CardUtil;
import com.wther.tromso.strategy.IllegalPlayException;
import com.wther.tromso.strategy.PuzzleEngine;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Layout widget similar to table but adds the buttons for cards
 * Created by Barnabas on 10/30/2014.
 */
public class HandLayout extends TableLayout {

    private Context context;

    private PuzzleEngine clickHandler;

    public HandLayout(Context context) {
        super(context);
        this.context = context;
    }

    public HandLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * Sets cards
     *
     * @param cards
     */
    public void addCards(List<Card> cards) {

        if(getChildCount() != 0){
            throw new IllegalStateException("addCards expects an empty table");
        }

        addRow(CardUtil.selectSuit(cards, Card.Suit.SPADES));
        addRow(CardUtil.selectSuit(cards, Card.Suit.HEARTS));
        addRow(CardUtil.selectSuit(cards, Card.Suit.DIAMONDS));
        addRow(CardUtil.selectSuit(cards, Card.Suit.CLUBS));
    }

    /**
     * Add a new row to the table
     *
     * @param cards
     */
    private void addRow(List<Card> cards){
        TableRow tableRow = new TableRow(context);

        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return Card.RANKS.indexOf(rhs.getRank()) - Card.RANKS.indexOf(lhs.getRank());
            }
        });

        // Add each card
        for(Card card : cards){
            CardButton button = new CardButton(context);
            button.setCard(card);
            button.setPlayable(true);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    CardButton buttonClicked = (CardButton)view;
                    try {
                        clickHandler.playCard(buttonClicked.getCard().getValue());
                    } catch (IllegalPlayException e){
                        throw new RuntimeException(e);
                    }
                }
            });

            tableRow.addView(button);
        }

        this.addView(tableRow, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setClickHandler(PuzzleEngine clickHandler) {
        this.clickHandler = clickHandler;
    }

    /**
     * Find a card in the table and hide it
     * @param card
     */
    public void hideCard(Card card) {
        // For each row
        for(int i = 0; i < getChildCount(); i++){
            TableRow row = (TableRow) getChildAt(i);

            // For each cell
            for(int j = 0; j < row.getChildCount(); j++){
                CardButton button = (CardButton) row.getChildAt(j);
                if(button.getCard().equals(card)){
                    button.setVisibility(View.INVISIBLE);
                    return;
                }
            }
        }
    }
}

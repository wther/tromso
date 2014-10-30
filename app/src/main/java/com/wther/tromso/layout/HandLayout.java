package com.wther.tromso.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.wther.tromso.model.Card;
import com.wther.tromso.model.util.CardUtil;

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
            Button button = new Button(context);
            button.setText(card.getValue());
            tableRow.addView(button);
        }

        this.addView(tableRow, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }
}

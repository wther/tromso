package com.wther.tromso.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.wther.tromso.model.Card;

/**
 * Created by Barnabas on 11/2/2014.
 */
public class CardButton extends Button {

    /**
     * Android Context
     */
    private Context context;

    /**
     * Card shown here
     */
    private Card card;

    /**
     * Value indicating whether card is playable
     */
    private boolean playable;

    public CardButton(Context context) {
        super(context);
        this.context = context;
    }

    public CardButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setCard(Card card) {
        this.card = card;
        this.setText(card.getValue());
    }

    public Card getCard() {
        return card;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
        this.setEnabled(this.playable);
    }
}

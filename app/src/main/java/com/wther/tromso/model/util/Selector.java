package com.wther.tromso.model.util;

/**
 * A selector class can select certain elements
 *
 * Created by Barnabas on 10/30/2014.
 */
public interface Selector<T> {

    /**
     * Judge function
     *
     * @param item Item to be judged for selection
     *
     * @return True if item is selected or false otherwise
     */
    boolean isSelected(T item);
}

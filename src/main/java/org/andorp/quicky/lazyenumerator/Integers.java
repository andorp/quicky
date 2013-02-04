package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public final class Integers implements Enumeration<Integer> {

    private int state;
    private boolean hasMore;

    private final int last;

    public Integers() {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Integers(int start, int _last) {
        state = start;
        last  = _last;
    }

    // @Override
    public boolean hasMoreElements() {
        return hasMore;
    }

    // @Override
    public Integer nextElement() {
        if(hasMore && state <= last) {
             int s = state;
             hasMore = (s == last);
             state++;
             return new Integer(s);
        } else throw new NoSuchElementException(); 
    }

    public static Integers STARTS_FROM(int start) {
        return new Integers(start, Integer.MAX_VALUE);
    }

    public static Integers ENDS_WITH(int last) {
        return new Integers(Integer.MIN_VALUE, last);
    }

}

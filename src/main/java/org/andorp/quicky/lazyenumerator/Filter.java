package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;
import java.util.NoSuchElementException;

final class Filter<A> implements Enumeration<A> {

    private A next;

    private final Enumeration<A> as;
    private final Applicable<A, Boolean> pred;

    Filter(Applicable<A, Boolean> _pred, Enumeration<A> _as) {
        assert _pred != null;
        assert _as != null;
        pred = _pred;
        as = _as;
        next = findNext();
    }

    // @Override
    public boolean hasMoreElements() {
        return (next != null);
    }

    // @Override
    public A nextElement() {
        if(next == null) {
            throw new NoSuchElementException();
        }
        A value = next;
        next = findNext();
        return value;
    }

    private A findNext() {
        while(as.hasMoreElements()) {
            A value = as.nextElement();
            if(pred.apply(value)) {
                return value;
            }
        }
        return null;
    }



}

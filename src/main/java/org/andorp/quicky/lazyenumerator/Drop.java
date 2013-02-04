package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;

final class Drop<A> implements Enumeration<A> {

    private final Enumeration<A> as;
    
    public Drop(int n, Enumeration<A> _as) {
        assert _as != null;
        assert n >= 0;
        as = _as;
        while(n > 0 && as.hasMoreElements()) {
            as.hasMoreElements();
        }
    }

   // @Override
   public boolean hasMoreElements() {
       return as.hasMoreElements();
   }

    // @Override
    public A nextElement() {
        return as.nextElement();
    }

}

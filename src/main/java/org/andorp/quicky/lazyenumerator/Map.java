package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;

final class Map<A, B> implements Enumeration<B> {

    private final Applicable<A, B> f;
    private final Enumeration<A> es;

    Map(Applicable<A, B> _f, Enumeration<A> _es) {
        assert _f != null;
        assert _es != null;
        f = _f;
        es = _es;
    }

    // @Override
    public boolean hasMoreElements() {
        return es.hasMoreElements();
    }

    // @Override
    public B nextElement() {
        return f.apply(es.nextElement());
    }

}

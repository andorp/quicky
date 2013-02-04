package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;

final class ZipWith<A, B, C> implements Enumeration<C> {

    private final Enumeration<A> as;
    private final Enumeration<B> bs;
    private final Applicable2<A,B,C> f2;

    ZipWith(Applicable2<A,B,C> _f2, Enumeration<A> _as, Enumeration<B> _bs) {
        assert _as != null;
        assert _bs != null;
        assert _f2 != null;
        as = _as;
        bs = _bs;
        f2 = _f2;
    }

    // @Override
    public boolean hasMoreElements() {
        return as.hasMoreElements() && bs.hasMoreElements();
    }

    // @Override
    public C nextElement() {
        A a = as.nextElement();
        B b = bs.nextElement();
        return f2.apply(a, b);
    }

}

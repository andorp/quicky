package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;

public final class Const<C> implements Enumeration<C> {

    private final C value;

    public Const(C _value) {
        assert _value != null;
        value = _value;
    }

    // @Override
    public boolean hasMoreElements() {
        return true;
    }

    // @Override
    public C nextElement() {
        return value;
    }

}

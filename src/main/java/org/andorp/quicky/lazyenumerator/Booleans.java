package org.andorp.quicky.lazyenumerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public final class Booleans implements Enumeration<Boolean> {

    private final Enumeration<Boolean> booleans;

    public Booleans() {
        ArrayList<Boolean> _booleans = new ArrayList<Boolean>();
        _booleans.add(Boolean.FALSE);
        _booleans.add(Boolean.TRUE);
        booleans = Collections.enumeration(_booleans);
    }

    // @Override
    public boolean hasMoreElements() {
        return booleans.hasMoreElements();
    }

    // @Override
    public Boolean nextElement() {
        return booleans.nextElement();
    }

}

package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;

public interface CloneableEnumeration<T> extends Enumeration<T> {

    public CloneableEnumeration<T> cloneEnumeration();

}

package org.andorp.quicky;

import java.util.ArrayList;
import java.util.Collection;

public final class RoseTree<T> {
    
    private final T element;
    private final ArrayList<RoseTree<T>> branches;
    
    public RoseTree(T element) {
        this.element = element;
        branches = new ArrayList<RoseTree<T>>(0);
    }
    
    public RoseTree(T element, Collection<RoseTree<T>> branches) {
        this.element = element;
        this.branches = new ArrayList<RoseTree<T>>(branches.size());
        for(RoseTree<T> branch : branches) {
            this.branches.add(branch);
        }
    }
    
    public T element() {
        return element;
    }
    
    public Collection<RoseTree<T>> branches() {
        return branches;
    }
}

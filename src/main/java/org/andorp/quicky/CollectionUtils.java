package org.andorp.quicky;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public final class CollectionUtils {
    
    private CollectionUtils() {
    }
    
    public static <T> Collection<T> shallowCopy(Collection<T> collection) {
        ArrayList<T> copied = new ArrayList<T>(collection.size());
        for(T element : collection) {
            copied.add(element);
        }
        return copied;
    }
    
    public static <T> List<T> shallowCopyToList(Collection<T> collection) {
        ArrayList<T> copied = new ArrayList<T>(collection.size());
        for(T element : collection) {
            copied.add(element);
        }
        return copied;
    }
    
}
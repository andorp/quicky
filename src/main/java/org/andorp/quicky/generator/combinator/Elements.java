package org.andorp.quicky.generator.combinator;

import java.util.Collection;
import java.util.List;

import org.andorp.quicky.Choose;
import org.andorp.quicky.CollectionUtils;
import org.andorp.quicky.IGenerator;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.RandomAndSize;


/**
 * Randomly generates one of the given values.
 * 
 * @author and.or
 *
 * @param <T> Type of the elements
 */
public final class Elements<T> implements IGenerator<T> {
    
    private final List<T> elements;
    private final Choose<Integer> choose;
    
    /**
     * @param elements Must be not null nor empty collection.
     */
    public Elements(Collection<T> elements) {
        Sanity.notNull(elements, "Elements: collection can't be null");
        Sanity.nonEmptyCollection(elements, "elements: collection can't be empty");
        this.elements = CollectionUtils.shallowCopyToList(elements);
        this.choose = new Choose<Integer>(
                PrimitiveRandomGen.integer(),
                0,
                (this.elements.size() - 1)
                );
    }
    
    /* Generates one of the given values */
    public T next(RandomAndSize random) {
        return elements.get(choose.next(random));
    }
    
}

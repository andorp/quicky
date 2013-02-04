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
 * Randomly selects one of the given generators.
 * 
 * @author and.or
 *
 * @param <T> Type of the elements
 */
public final class OneOf<T> implements IGenerator<IGenerator<T>> {
    
    private final List<IGenerator<T>> generators;
    private final Choose<Integer> choose;
    
    /**
     * @param generators Must be not null nor empty collection.
     */
    public OneOf(Collection<IGenerator<T>> generators) {
        Sanity.notNull(generators, "OneOf: generator collection can't be null");
        Sanity.nonEmptyCollection(generators, "OneOf: generator collection can't be empty");
        this.generators = CollectionUtils.shallowCopyToList(generators);
        this.choose = new Choose<Integer>(
                PrimitiveRandomGen.integer(),
                0,
                (this.generators.size() - 1)
                );
    }
    
    /* Randomly uses one of the given generators
     */
    public IGenerator<T> next(RandomAndSize random) {
        return generators.get(choose.next(random));
    }
    
}

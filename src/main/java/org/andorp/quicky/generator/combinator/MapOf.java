package org.andorp.quicky.generator.combinator;


import java.util.Map;
import java.util.HashMap;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.mutable.MutableChoose;

/**
 * Randomly generates a Map filled up with data generated by the generators
 * 
 * @author and.or
 *
 * @param <K> The type of the keys
 * @param <V> The type of the values
 */
public final class MapOf<K,V> implements IGenerator<Map<K,V>> {
    
    private final IGenerator<K> keyGenerator;
    private final IGenerator<V> valueGenerator;
    private final MutableChoose<Integer> choose;
    
    /**
     * @param keyGenerator The keyGenerator can not be null
     * @param valueGenerator The valueGenerator can not be null
     */
    public MapOf(IGenerator<K> keyGenerator, IGenerator<V> valueGenerator) {
        Sanity.notNull(keyGenerator, "The keyGenerator can't be null.");
        Sanity.notNull(valueGenerator, "The valueGenerator can't be null.");
        
        this.keyGenerator = keyGenerator;
        this.valueGenerator = valueGenerator;
        this.choose = new MutableChoose<Integer>(PrimitiveRandomGen.integer());
    }
    
    public Map<K,V> next(RandomAndSize random) {
        
        final HashMap<K,V> map = new HashMap<K,V>();
        final int size = random.size();
        choose.setLimits(0,size);
        final int numberOfElems = choose.next(random);
        for(int ei = 0; ei < numberOfElems; ++ei) {
            final K key = keyGenerator.next(random);
            final V value = valueGenerator.next(random);
            map.put(key, value);
        }
        return map;
        
    }
    
}

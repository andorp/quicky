package org.andorp.quicky.generator.combinator;

import java.util.ArrayList;
import java.util.List;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.Elements;
import org.testng.annotations.Test;
import org.testng.Assert;


/**
 * TEST-DESIGN Equaivalance partiotioning
 *
 * Partitions based on collection length
 * - 0 elements
 * - 1 element
 * - n elements
 *
 * Properties:
 * - All elements get selected
 *
 * @author and.or
 *
 */
public final class Stage2TsElementsTest {
    
    /**
     * Partition: 1 element is in the collection
     * Property: The element get selected
     */
    @Test(groups = {"STAGE-2"})
    public void elementsOneElementTest() {
        final int seed = 95609165;
        final int repetition = 1000;
        final int value = 0;
        
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final List<Integer> integers = new ArrayList<Integer>(0);
        integers.add(value);
        final Elements<Integer> elements = new Elements<Integer>(integers);
        
        for(int size = 1; size < (Integer.MAX_VALUE / 2); size *= 2) {
            for(int i = 0; i < repetition; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final int e = elements.next(randomAndSize);
                Assert.assertEquals(e, value, "Elements: selected value was inappropaite");
            }
        }
    }
    
    /**
     * Partition: More element is in the collection
     * Property: The element get selected
     */
    @Test(groups = {"STAGE-2"})
    public void elementsMoreElementTest() {
        final int seed = 95609165;
        final int repetition = 1000;
        final int value = 100;
        
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final List<Integer> integers = new ArrayList<Integer>(0);
        for(int i = 0; i < value; ++i) {
            integers.add(i);
        }
        
        final Elements<Integer> elements = new Elements<Integer>(integers);
        
        for(int size = 1; size < (Integer.MAX_VALUE / 2); size *= 2) {
            for(int i = 0; i < repetition; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final int e = elements.next(randomAndSize);
                Assert.assertTrue(integers.contains(e), "Elements: selected value was inappropaite: " + e);
            }
        }
    }
    
}

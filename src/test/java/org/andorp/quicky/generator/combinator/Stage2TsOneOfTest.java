package org.andorp.quicky.generator.combinator;

import java.util.Collection;
import java.util.ArrayList;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.OneOf;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * TEST-DESIGN Equivalance partitioning
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
public final class Stage2TsOneOfTest {
    
    @Test(groups = {"STAGE-2"})
    public void oneOfOneElementTest() {
        final int seed = 95609165;
        final int repetition = 1000;
        final int value = 0;
        
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final Collection<IGenerator<Integer>> generators = new ArrayList<IGenerator<Integer>>(1);
        generators.add(new ConstGenerator<Integer>(value));
        final OneOf<Integer> elements = new OneOf<Integer>(generators);
        
        for(int size = 1; size < (Integer.MAX_VALUE / 2); size *= 2) {
            for(int i = 0; i < repetition; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final IGenerator<Integer> g = elements.next(randomAndSize);
                Assert.assertTrue(generators.contains(g), "OneOf: selected value was inappropaite");
            }
        }
    }
    
    @Test(groups = {"STAGE-2"})
    public void oneOfMoreElementTest() {
        final int seed = 959165;
        final int repetition = 1000;
        final int value = 20;
        
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final Collection<IGenerator<Integer>> generators = new ArrayList<IGenerator<Integer>>(1);
        for(int i = 0; i < value; ++i) {
            generators.add(new ConstGenerator<Integer>(i));
        }
        final OneOf<Integer> elements = new OneOf<Integer>(generators);
        
        for(int size = 1; size < (Integer.MAX_VALUE / 2); size *= 2) {
            for(int i = 0; i < repetition; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                IGenerator<Integer> g = elements.next(randomAndSize);
                Assert.assertTrue(generators.contains(g), "OneOf: selected value was inappropaite");
            }
        }
    }
    
}

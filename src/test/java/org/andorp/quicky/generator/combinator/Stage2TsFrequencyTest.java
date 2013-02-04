package org.andorp.quicky.generator.combinator;

import java.util.Collection;
import java.util.ArrayList;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IRandom;
import org.andorp.quicky.Pair;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.Frequency;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * TEST-DESIGN Equivalance partitioning
 * 
 * Partition based on collection length
 * - 0 elements
 * - 1 element
 * - n elements
 * 
 * Partition based in frequency values
 * - 
 * 
 * Properties:
 * - All elements get selected
 * 
 * @author and.or
 *
 */
public final class Stage2TsFrequencyTest {
    
    @Test(groups = {"STAGE-2"})
    public void frequencyOneElementTest() {
        final int seed = 95609165;
        final int repetition = 1000;
        final int value = 0;
        
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final Collection<Pair<Integer, IGenerator<Integer>>>
        generators = new ArrayList<Pair<Integer, IGenerator<Integer>>>(1);
        
        generators.add(new Pair<Integer, IGenerator<Integer>>(
                new Integer(1),
                new ConstGenerator<Integer>(value)
                ));
        final Frequency<Integer> elements = new Frequency<Integer>(generators);
        
        for(int size = 1; size < (Integer.MAX_VALUE / 2); size *= 2) {
            for(int i = 0; i < repetition; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final IGenerator<Integer> g = elements.next(randomAndSize);
                searchGenerator(generators, g);
            }
        }
    }
    
    @Test(groups = {"STAGE-2"})
    public void frequencyHomogenMoreElementsTest() {
        final int seed = 95609165;
        final int repetition = 1000;
        final int value = 100;
        
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final Collection<Pair<Integer, IGenerator<Integer>>>
        generators = new ArrayList<Pair<Integer, IGenerator<Integer>>>(1);
        
        for(int v = 0; v < value; ++v) {
            generators.add(new Pair<Integer, IGenerator<Integer>>(
                    new Integer(1),
                    new ConstGenerator<Integer>(v)
                    ));
        }
        final Frequency<Integer> elements = new Frequency<Integer>(generators);
        
        for(int size = 1; size < (Integer.MAX_VALUE / 2); size *= 2) {
            for(int i = 0; i < repetition; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final IGenerator<Integer> g = elements.next(randomAndSize);
                searchGenerator(generators, g);
            }
        }
    }
    
    @Test(groups = {"STAGE-2"})
    public void frequencyNonHomogenMoreElementsTest() {
        final int seed = 95609165;
        final int repetition = 1000;
        final int value = 100;
        
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final Collection<Pair<Integer, IGenerator<Integer>>>
        generators = new ArrayList<Pair<Integer, IGenerator<Integer>>>(1);
        
        for(int v = 0; v < value; ++v) {
            generators.add(new Pair<Integer, IGenerator<Integer>>(
                    new Integer((v % 10) + 1),
                    new ConstGenerator<Integer>(v)
                    ));
        }
        final Frequency<Integer> elements = new Frequency<Integer>(generators);
        
        for(int size = 1; size < (Integer.MAX_VALUE / 2); size *= 2) {
            for(int i = 0; i < repetition; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final IGenerator<Integer> g = elements.next(randomAndSize);
                searchGenerator(generators, g);
            }
        }
    }    
    
    private void searchGenerator(
            Collection<Pair<Integer, IGenerator<Integer>>> pairs,
            IGenerator<Integer> g
            ) {
        for(Pair<Integer, IGenerator<Integer>> pair : pairs) {
            if(pair.second().equals(g)) {
                return;
            }
        }
        Assert.fail("Selected generator not found in originally given set");
    }
    
    
}

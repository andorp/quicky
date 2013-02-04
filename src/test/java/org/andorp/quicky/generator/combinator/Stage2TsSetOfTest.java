package org.andorp.quicky.generator.combinator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.Elements;
import org.andorp.quicky.generator.combinator.SetOf;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Stage2TsSetOfTest {
    
    @Test(groups = {"STAGE-2"})
    public void setContainementTest() {
        final int seed = 98;
        final int limit = 1000;
        final Collection<Integer> elems = new ArrayList<Integer>();
        for(int i = 0; i < limit; ++i) {
            elems.add(i);
        }
        final Elements<Integer> elements = new Elements<Integer>(elems);
        final SetOf<Integer> setOf = new SetOf<Integer>(elements);
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        
        for(int size = 1; size < 10000; size *= 2) {
            for(int i = 0; i < 100; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final Set<Integer> set = setOf.next(randomAndSize);
                checkSet(set, elems);
            }
        }
    }
    
    private void checkSet(Set<Integer> set, Collection<Integer> values) {
        for(Integer i : set) {
            Assert.assertTrue(values.contains(i), "Collection does not contain the give value:" + i);
        }
    }
}

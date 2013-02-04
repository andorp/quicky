package org.andorp.quicky.generator.combinator;

import java.util.Map;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.MapOf;
import org.andorp.quicky.generator.primitive.IntegerGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;


public final class Stage2TsMapOfTest {
    
    @Test(groups = {"STAGE-2"})
    public void checkMapOf() {
        
        final int seed = 937074762;
        final int repetition = 100;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        
        final MapOf<Integer, Integer> mapOf = new MapOf<Integer, Integer>(new IntegerGenerator(), new IntegerGenerator());
        
        for(int r = 0; r < repetition; ++r) {
            for(int s = 1; s < 1000000; s *= 2) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, s);
                final Map<Integer, Integer> map = mapOf.next(randomAndSize);
                checkMap(map, s);
            }
        }
    }
    
    private void checkMap(Map<Integer, Integer> map, int size) {
        Assert.assertNotNull(map, "Generatoed map is null");
        for(Integer i : map.keySet()) {
            Assert.assertNotNull(i, "Generated key was null");
        }
        for(Integer i : map.values()) {
            Assert.assertNotNull(i, "Generated value was null");
        }
    }
}

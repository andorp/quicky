package org.andorp.quicky.generator.combinator;

import java.util.HashSet;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.primitive.ByteGenerator;
import org.andorp.quicky.generator.combinator.stated.Unique;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Stage2TsUniqueTest {

    @Test(groups = {"STAGE-2"})
    public void uniquenessTest() {
        final int seed = 7542;
        final int limit = 1000;
        final Unique<Byte> q = new Unique<Byte>(new ByteGenerator(), 65536);
        final HashSet<Byte> generated = new HashSet<Byte>();
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);

        for(int size = 1; size < 10000; size *= 2) {
           for(int i = 0; i < 100; ++i) {
               final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
               final Byte b = q.next(randomAndSize);
               if(generated.contains(b)) {
                   Assert.fail("Byte was already generated");
               }
           }
        }
    }

}

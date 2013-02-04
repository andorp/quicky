package org.andorp.quicky.generator.combinator;

import org.andorp.quicky.generator.combinator.MapOf;
import org.testng.annotations.Test;

public final class Stage1TsMapOfTest {
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void nullKeyGeneratorSanityCheck() {
        new MapOf<Integer, Integer>(null, new ConstGenerator<Integer>(6));
    }
    
    @Test(groups ={"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void nullValueGeneratorSanityCheck() {
        new MapOf<Integer, Integer>(new ConstGenerator<Integer>(8), null);
    }
    
}

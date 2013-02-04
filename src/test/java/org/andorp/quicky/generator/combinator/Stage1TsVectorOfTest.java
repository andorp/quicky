package org.andorp.quicky.generator.combinator;

import org.andorp.quicky.generator.combinator.VectorOf;
import org.testng.annotations.Test;


public final class Stage1TsVectorOfTest {
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void nullSanityCheck() {
        new VectorOf<Integer>(1, null);
    }
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void zeroSanityCheck() {
        final ConstGenerator<Integer> g = new ConstGenerator<Integer>(5);
        new VectorOf<Integer>(0, g);
    }
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void negativeSanityCheck() {
        final ConstGenerator<Integer> g = new ConstGenerator<Integer>(5);
        new VectorOf<Integer>(-1, g);
    }
    
}

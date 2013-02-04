package org.andorp.quicky.generator.combinator;

import org.andorp.quicky.generator.combinator.ListOf;
import org.testng.annotations.Test;

public final class Stage1TsListOfTest {
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void nullSanityCheck1() {
        new ListOf<Integer>(null);
    }
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void nullSanityCheck2() {
        new ListOf<Integer>(0, null);
    }
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void negativeSanityCheck() {
        new ListOf<Integer>((-1), new ConstGenerator<Integer>(6));
    }
    
}

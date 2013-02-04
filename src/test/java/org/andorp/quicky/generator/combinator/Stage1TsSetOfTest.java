package org.andorp.quicky.generator.combinator;

import org.andorp.quicky.generator.combinator.SetOf;
import org.testng.annotations.Test;


public final class Stage1TsSetOfTest {
    
    /**
     * Sanity check: Null parameter is not accepted
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void nullParameterSetOfTest() {
        new SetOf<Integer>(null);
    }
    
}

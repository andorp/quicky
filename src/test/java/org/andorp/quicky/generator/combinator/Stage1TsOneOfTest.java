package org.andorp.quicky.generator.combinator;

import java.util.ArrayList;
import java.util.List;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.generator.combinator.OneOf;
import org.testng.annotations.Test;


public final class Stage1TsOneOfTest {
    
    /**
     * Sanity check: Null parameter is not accepted
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void oneOfNullParameterTest() {
        final OneOf<Integer> oneOf = new OneOf<Integer>(null);
    }
    
    /**
     * Sanity check: Empty collection is not accepted
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void emptyParameterTest() {
        final List<IGenerator<Integer>> list = new ArrayList<IGenerator<Integer>>();
        final OneOf<Integer> oneOf = new OneOf<Integer>(list);
    }
    
}

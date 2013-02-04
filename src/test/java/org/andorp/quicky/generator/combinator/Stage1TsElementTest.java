package org.andorp.quicky.generator.combinator;

import java.util.ArrayList;
import java.util.List;

import org.andorp.quicky.generator.combinator.Elements;
import org.testng.annotations.Test;


public final class Stage1TsElementTest {
    
    /**
     * Sanity check: Null parameter is not accepted
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void sanityTest() {
        new Elements<Integer>(null);
    }
    
    /**
     * Sanity check: Empty collection is not accepted
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void sanityEmptyCollection() {
        final List<Integer> integers = new ArrayList<Integer>();
        new Elements<Integer>(integers);
    }
    
}

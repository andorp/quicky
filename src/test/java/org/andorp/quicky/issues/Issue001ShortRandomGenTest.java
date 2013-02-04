package org.andorp.quicky.issues;

import org.andorp.quicky.engine.ConstRandom;
import org.andorp.quicky.random.ShortRandomGen;
import org.testng.annotations.Test;
import org.testng.Assert;


public final class Issue001ShortRandomGenTest {
    
    /**
     * When the issue occurs we get divide by zero exception
     */
    @Test(groups = {"ISSUE"})
    public void produceIssue() {
        final ShortRandomGen shortRandomGen = new ShortRandomGen();
        shortRandomGen.random(new ConstRandom(0,0), Short.MIN_VALUE, Short.MAX_VALUE);
    }
    
}

package org.andorp.quicky.engine;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.RandomAndSize;
import org.testng.Assert;
import org.testng.annotations.Test;


public final class Stage1TsRandomAndSizeTest {
    
    /**
     * NO TEST-DESIGN IS REQUIRED
     * Check if argument validation works the first parameter
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void nullSanityCheck() {
        RandomAndSize ras = new RandomAndSize(null, 0);
    }
    
    /**
     * TEST-DESIGN: BOUNDARY VALUE on size parameter (-1)
     * Check id argument validation works for the second parameter
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void negativeSizeSanityCheck() {
        final ConstRandom r = new ConstRandom(0,0);
        final RandomAndSize ras = new RandomAndSize(r, -1);
    }
    
    private void checkIfReferenceAreHeld(final IRandom r, final int s, final RandomAndSize ras) {
        Assert.assertEquals(ras.random(), r, "RandomAndSize random object had different reference");
        Assert.assertEquals(ras.size(), s, "RandomAndSize size had different value");
    }
    
    /**
     * TEST-DESIGN: BOUNDARY VALUE on size parameter (0)
     * Check if argument validation works for the second parameter
     * Check if the object itself is immutable
     * Check if the object holds the same reference as given to the constructor
     */
    @Test(groups = {"STAGE-1"})
    public void sameReference() {
        // Parameters //
        final int size0 = 0;
        final ConstRandom r = new ConstRandom(0,0);
        final RandomAndSize ras = new RandomAndSize(r, size0);
        
        // Check if the object is immutable //
        checkIfReferenceAreHeld(r, size0, ras);
        checkIfReferenceAreHeld(r, size0, ras);
    }
}

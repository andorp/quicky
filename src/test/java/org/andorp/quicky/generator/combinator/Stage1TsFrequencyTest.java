package org.andorp.quicky.generator.combinator;

import java.util.ArrayList;
import java.util.List;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.Pair;
import org.andorp.quicky.generator.combinator.Frequency;
import org.testng.annotations.Test;


public final class Stage1TsFrequencyTest {
    
    /**
     * Sanity check: Null parameter is not accepted
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void frequencyNullParameterTest() {
        new Frequency<Integer>(null);
    }
    
    /**
     * Sanity check: Empty collection is not accepted
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void emptyParameterFrequencyTest() {
        final List<Pair<Integer, IGenerator<Integer>>>
        list = new ArrayList<Pair<Integer, IGenerator<Integer>>>();
        
        new Frequency<Integer>(list);
    }
    
    /**
     * Sanity check: Frequency value can't be zero
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void zeroValuedFrequency() {
        final List<Pair<Integer, IGenerator<Integer>>>
        list = new ArrayList<Pair<Integer, IGenerator<Integer>>>();
        
        list.add(new Pair(new Integer(0), new ConstGenerator(0)));
        
        new Frequency<Integer>(list);
    }
    
    /**
     * Sanity check: Frequency value can't be negative
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void negativeValuedFrequency() {
        final List<Pair<Integer, IGenerator<Integer>>>
        list = new ArrayList<Pair<Integer, IGenerator<Integer>>>();
        
        list.add(new Pair(new Integer(-1), new ConstGenerator(0)));
        
        new Frequency<Integer>(list);
    }
    
    /**
     * Sanity check: Frequency value can't be negative
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void nullValuedFrequency() {
        final List<Pair<Integer, IGenerator<Integer>>>
        list = new ArrayList<Pair<Integer, IGenerator<Integer>>>();
        
        list.add(new Pair(new Integer(1), null));
        
        new Frequency<Integer>(list);
    }
    
}

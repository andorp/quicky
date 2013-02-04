package org.andorp.quicky.generator.combinator;

import java.util.List;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.ListOf;
import org.testng.Assert;
import org.testng.annotations.Test;


public final class Stage2TsListOfTest {
    
    @Test(groups = {"STAGE-2"})
    public void listOfTest() {
        final ConstGenerator<Integer> g = new ConstGenerator<Integer>(6);
        final int min = 0;
        listOfTest(min, new ListOf<Integer>(g));
    }
    
    @Test(groups = {"STAGE-2"})
    public void explicitZeroLengthListOfTest() {
        final ConstGenerator<Integer> g = new ConstGenerator<Integer>(6);
        final int min = 0;
        listOfTest(min, new ListOf<Integer>(min, g));
    }
    @Test(groups = {"STAGE-2"})
    public void explicitMoreThanZeroLengthListOfTest() {
        final ConstGenerator<Integer> g = new ConstGenerator<Integer>(6);
        final int min = 10;
        listOfTest(min, new ListOf<Integer>(min, g));
    }
    
    private void listOfTest(int min, ListOf<Integer> list) {
        final int seed = 562301;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        
        for(int r = 0; r < 10000; ++r) {
            final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, r);
            final List<Integer> is = list.next(randomAndSize);
            checkGeneratedList(min, r, is);
        }
    }
    
    private void checkGeneratedList(int min, int max, List<Integer> is) {
        final int length = is.size();
        final int x = min >= max ? min : max;
        Assert.assertTrue(length >= min, "The length of the list is lesser than the minimum: " + length + " >= " + min);
        Assert.assertTrue(length <= x, "The length of the list is greater than the maximum: " + length + " <= " + x);
        for(Integer i : is) {
            Assert.assertNotNull(i, "Generated object was null");
        }
    }
}


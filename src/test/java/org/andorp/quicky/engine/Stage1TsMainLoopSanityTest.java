package org.andorp.quicky.engine;

import org.andorp.quicky.engine.ConstRandom;
import org.andorp.quicky.engine.MainLoop;
import org.andorp.quicky.engine.State;
import org.testng.annotations.Test;


public final class Stage1TsMainLoopSanityTest {
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void sanityRandomParameterCheck() {
        new MainLoop(null, new NullPostProcess());
    }
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void sanityPostParameterCheck() {
        new MainLoop(new ConstRandom(0,0), null);
    }
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void sanityMainLoopTestFirstParameter() {
        // Create environment //
        final ConstRandom random = new ConstRandom(0,0);
        final NullPostProcess postProcess = new NullPostProcess();
        final MainLoop mainLoop = new MainLoop(random, postProcess);
        
        mainLoop.test(null, new ControllablePropGenerator());
    }
    
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void sanityMainLoopTestSecondParameter() {
        // Create environment //
        final ConstRandom random = new ConstRandom(0,0);
        final NullPostProcess postProcess = new NullPostProcess();
        final MainLoop mainLoop = new MainLoop(random, postProcess);
        
        mainLoop.test(new State(), null);
    }
    
}

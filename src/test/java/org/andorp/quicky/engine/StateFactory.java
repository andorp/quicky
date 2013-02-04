package org.andorp.quicky.engine;

import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.Arguments;
import org.andorp.quicky.engine.DefaultSizeComputation;
import org.andorp.quicky.engine.ISizeComputation;
import org.andorp.quicky.engine.ITerminal;
import org.andorp.quicky.engine.NullTerminal;
import org.andorp.quicky.engine.State;

public final class StateFactory {
    
    private final ITerminal terminal;
    private final ISizeComputation sizeComputation;
    
    public StateFactory(ITerminal terminal, ISizeComputation sizeComputation) {
        Sanity.notNull(terminal, "StateFactory: terminal parameter can't be null");
        Sanity.notNull(sizeComputation, "StateFactory: sizeComputation parameter can't be null");
        this.terminal = terminal;
        this.sizeComputation = sizeComputation;
    }
    
    public StateFactory(Arguments arguments) {
        Sanity.notNull(arguments, "StateFactory: arguments parameter can't be null");
        terminal = new NullTerminal();
        sizeComputation =  new DefaultSizeComputation(arguments);
    }
    
    private State emptyState() {
        final State state = new State();
        state.computeSize = sizeComputation;
        state.terminal = terminal;
        state.maxDiscardedTests = 1;
        state.numDiscardedTests = 0;
        state.maxSuccessTests = 1;
        state.numSuccessShrinks = 0;
        state.numSuccessTests = 0;
        state.numTotTryShrinks = 0;
        state.numTryShrinks = 0;
        state.randomSeed = 0;
        return state;
    }
    
    State createSuccessState(int success) {
        final State state = emptyState();
        state.maxSuccessTests = success;
        return state;
    }
    
    State createGaveUpState(int success, int gaveUp) {
        final State state = emptyState();
        state.maxSuccessTests = success;
        state.maxDiscardedTests = gaveUp;
        return state;
    }
    
}

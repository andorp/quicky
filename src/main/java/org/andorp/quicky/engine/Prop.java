package org.andorp.quicky.engine;

import org.andorp.quicky.RoseTree;
import org.andorp.quicky.engine.SingleTestResult;

public final class Prop {
    
    private final RoseTree<SingleTestResult> result;
    private final Object[] args;
    
    public Prop(SingleTestResult result, Object[] args) {
        this.result = new RoseTree<SingleTestResult>(result);
        this.args = args;
    }
    
    public Prop(RoseTree<SingleTestResult> result, Object[] args) {
        this.result = result;
        this.args = args;
    }
    
    public SingleTestResult result() {
        return result.element();
    }
    
    public Object[] shrinkedArguments() {
        return args;
    }
    
    public RoseTree<SingleTestResult> roseTree() {
        return result;
    }
    
}

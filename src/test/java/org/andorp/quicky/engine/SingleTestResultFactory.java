package org.andorp.quicky.engine;

import org.andorp.quicky.engine.SingleTestResult;

public class SingleTestResultFactory {
    
    public static enum Command {
        SUCCESS_POSITIVE,
        SUCCESS_NEGATIVE,
        FAIL_POSITIVE,
        FAIL_NEGATIVE,
        DISCARD_NEG_NEG,
        DISCARD_NEG_POS,
        DISCARD_POS_NEG,
        DISCARD_POS_POS,
        ABORTED
    };
    
    public static SingleTestResult create(Command command) {
        if(Command.SUCCESS_POSITIVE == command) {
            return SUCCESS_POSITIVE;
        }
        
        if(Command.SUCCESS_NEGATIVE == command) {
            return SUCCESS_NEGATIVE;
        }
        
        if(Command.FAIL_NEGATIVE == command) {
            return FAIL_POSITIVE;
        }
        
        if(Command.FAIL_POSITIVE == command) {
            return FAIL_POSITIVE;
        }
        
        if(Command.DISCARD_NEG_NEG == command) {
            return DISCARDED(false, false);
        }
        
        if(Command.DISCARD_NEG_POS == command) {
            return DISCARDED(false, true);
        }
        
        if(Command.DISCARD_POS_NEG == command) {
            return DISCARDED(true, false);
        }
        
        if(Command.DISCARD_POS_POS == command) {
            return DISCARDED(true, true);
        }
        
        throw new RuntimeException("Invalid path");
    }
    
    public static final SingleTestResult SUCCESS_POSITIVE = new SingleTestResult(
            false, true, true, "Test success positive", false
            );
    
    public static final SingleTestResult SUCCESS_NEGATIVE = new SingleTestResult(
            false, false, false, "Test success negative", false
            );
    
    public static final SingleTestResult FAIL_POSITIVE    = new SingleTestResult(
            false, false, true, "Test fail positive", false
            );
    
    public static final SingleTestResult FAIL_NEGATIVE    = new SingleTestResult(
            false, true, false, "Test fail positive", false
            );
    
    public static final SingleTestResult DISCARDED(boolean x, boolean y) {
        return new SingleTestResult(true, x, y, "Test is discarded", false);
    }
    
    public static final SingleTestResult ABORTED(boolean discarded, boolean ok, boolean expect) {
        return new SingleTestResult(discarded, ok, expect, "Test is aborted", true);
    }
    
}

package org.andorp.quicky;

import org.andorp.quicky.PreconditionError;

public final class Precondition {
    
    private Precondition() {}
    
    public static void fail(String message) {
        PreconditionError p = new PreconditionError();
        throw p;
    }
    
}

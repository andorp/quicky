package org.andorp.quicky;

public final class Postcondition {
    
    private Postcondition() {
    }
    
    public static void assertTrue(boolean value, String message) {
        if(!value) {
            throw new PostconditionError(message);
        }
    }
    
}

package org.andorp.quicky.engine;

public final class SingleTestResult {
    
    /**
     * indicates that if the test is discarded
     */
    public final boolean isDiscarded;
    
    /**
     * result of the test case
     */
    public final boolean ok;
    
    /**
     * indicates what the expected result of the property is
     */
    public final boolean expect;
    
    /**
     * a message indicating what went wrong
     */
    public final String reason;
    
    /**
     * if true, the test should not be repeated
     */
    public final boolean abort;
    
    public SingleTestResult(
            boolean isDiscarded,
            boolean ok,
            boolean expect,
            String reason,
            boolean abort
            ) {
        this.isDiscarded = isDiscarded;
        this.ok = ok;
        this.expect = expect;
        this.reason = reason;
        this.abort = abort;
    }
    
    @Override
    public Object clone() {
        return new SingleTestResult(
                isDiscarded,
                ok,
                expect,
                reason,
                abort
                );
    }
    
}

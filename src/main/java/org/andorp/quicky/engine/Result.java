package org.andorp.quicky.engine;

/* Result ADT */
public abstract class Result {
    
    public final int numTests;
    public final String output;
    
    protected Result(int numTests, String output) {
        this.numTests = numTests;
        this.output = output;
    }
    
    public static Result Success(int numTests, String output) {
        return new Success(numTests, output);
    }
    
    public static Result GaveUp(int numTests, String output) {
        return new Result.GaveUp(numTests, output);
    }
    
    public static Result Failure(
            int numTests,
            String output,
            int numShrinks,
            long usedSeed,
            int usedSize,
            String reason
            ) {
        return new Failure(
                numTests,
                output,
                numShrinks,
                usedSeed,
                usedSize,
                reason
                );
    }
    
    public static Result NoExpectedFailure(int numTests, String output) {
        return new NoExpectedFailure(numTests, output);
    }
    
    public static class Success extends Result {
        private Success(int numTests, String output) {
            super(numTests, output);
        }
    }
    
    public static class GaveUp extends Result {
        private GaveUp(int numTests, String output) {
            super(numTests, output);
        }
    }
    
    public static class Failure extends Result {
        public final int numShrinks;
        public final long usedSeed;
        public final int usedSize;
        public final String reason;
        private Failure(
                int numTests,
                String output,
                int numShrinks,
                long usedSeed,
                int usedSize,
                String reason
                ) {
            super(numTests, output);
            this.numShrinks = numShrinks;
            this.usedSeed = usedSeed;
            this.usedSize = usedSize;
            this.reason = reason;
        }
    }
    
    public static class NoExpectedFailure extends Result {
        private NoExpectedFailure(int numTests, String output) {
            super(numTests, output);
        }
    }
    
}

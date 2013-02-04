package org.andorp.quicky.engine;

import org.andorp.quicky.Sanity;

public final class Arguments implements Cloneable {
    
    public final boolean chatty;
    public final boolean replay;
    public final int maxSuccess;
    public final int maxSize;
    public final double maxDiscardRatio;
    
    public Arguments(
            boolean chatty,
            boolean replay,
            int maxSuccess,
            int maxSize,
            double maxDiscardRatio
            ) {
        Sanity.greaterThanZero(maxSuccess, "Arguments: maxSuccess must be greater than zero");
        Sanity.greaterThanZero(maxSize, "Arguments: maxSize must be greater than zero");
        this.chatty = chatty;
        this.replay = replay;
        this.maxSuccess = maxSuccess;
        this.maxSize = maxSize;
        this.maxDiscardRatio = maxDiscardRatio;
    }
    
    @Override
    public Object clone() {
        return new Arguments(
                chatty,
                replay,
                maxSuccess,
                maxSize,
                maxDiscardRatio
                );
    }
    
}

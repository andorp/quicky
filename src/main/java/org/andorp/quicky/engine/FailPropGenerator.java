package org.andorp.quicky.engine;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.Prop;
import org.andorp.quicky.engine.RandomAndSize;

public final class FailPropGenerator implements IGenerator<Prop> {
    
    private final String message;
    
    public FailPropGenerator(String message) {
        Sanity.notNull(message, "message can't be null");
        Sanity.notEmpty(message, "message can't be emtpy");
        this.message = message;
    }
    
    public Prop next(RandomAndSize random) {
        SingleTestResult result = new SingleTestResult(
                false, false, true, message, false
                );
        return new Prop(result, new Object[0]);
    }
    
}

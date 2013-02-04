package org.andorp.quicky.engine;

import java.util.Random;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.Prop;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.engine.SingleTestResultFactory.Command;


public class RandomStatedPropGenerator implements IGenerator<Prop> {
    
    private int success;
    private int discarded;
    private int failed;
    
    private Random random;
    
    private Command state;
    
    public RandomStatedPropGenerator(int success, int discarded, int failed) {
        Sanity.greaterOrEqualThanZero(success, "RandomStatedPropGenerator success parameter must be greater than zero");
        Sanity.greaterOrEqualThanZero(discarded, "RandomStatedPropGenerator discarded parameter must be greater than zero");
        Sanity.greaterOrEqualThanZero(failed, "RandomStatedPropGenerator failed  parameter must be greater than zero");
        this.success = success;
        this.discarded = discarded;
        this.failed = failed;
        
        random = new Random();
        
        state = Command.SUCCESS_POSITIVE;
    }
    
    // @Override
    public Prop next(RandomAndSize random) {
        return new Prop(SingleTestResultFactory.create(stateChange(state)), new Object[0]);
    }
    
    private Command stateChange(Command command) {
        if(success <= 0 && discarded <= 0 && failed <= 0) {
            return Command.FAIL_NEGATIVE;
        }
        
        int r = random.nextInt(5);
        switch(r) {
            case 0: 
                if(success > 0) {
                    --success;
                    return Command.SUCCESS_POSITIVE;
                } else return stateChange(state); 
            case 1:
                if(success > 0) {
                    --success;
                    return Command.SUCCESS_NEGATIVE;
                } else return stateChange(state); 
            case 2: 
                if(failed > 0) {
                    --failed;
                    return Command.FAIL_POSITIVE;
                } else return stateChange(state); 
            case 3: 
                if(failed > 0) {
                    --failed;
                    return Command.FAIL_NEGATIVE;
                } else return stateChange(state); 
            case 4:
                if(discarded > 0) {
                    --discarded;
                    r = random.nextInt(4);
                    if(r == 0) return Command.DISCARD_NEG_NEG;
                    else if(r == 1) return Command.DISCARD_NEG_POS;
                    else if(r == 2) return Command.DISCARD_POS_NEG;
                    else return Command.DISCARD_POS_POS; 
                } else return stateChange(state); 
        }
        throw new RuntimeException("Illegal Path: " + r);
    }
    
    public int failed() {
        return failed;
    }
    
    public int success() {
        return success;
    }
    
    public int discarded() {
        return discarded;
    }
}

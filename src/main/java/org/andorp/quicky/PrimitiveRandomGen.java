package org.andorp.quicky;

import org.andorp.quicky.IRandomGen;
import org.andorp.quicky.random.ByteRandomGen;
import org.andorp.quicky.random.CharacterRandomGen;
import org.andorp.quicky.random.IntegerRandomGen;
import org.andorp.quicky.random.LongRandomGen;
import org.andorp.quicky.random.ShortRandomGen;

public final class PrimitiveRandomGen {
    
    private PrimitiveRandomGen() {
    }
    
    public static IRandomGen<Integer> integer() {
        return new IntegerRandomGen();
    }
    
    public static IRandomGen<Character> character() {
        return new CharacterRandomGen();
    }
    
    public static IRandomGen<Byte> bytes() {
        return new ByteRandomGen();
    }
    
    public static IRandomGen<Long> longs() {
        return new LongRandomGen();
    }
    
    public static IRandomGen<Short> shorts() {
        return new ShortRandomGen();
    }
    
    //TODO: More primitives...
    
}
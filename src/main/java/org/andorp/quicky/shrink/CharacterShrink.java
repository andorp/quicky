package org.andorp.quicky.shrink;

import java.util.Enumeration;

import org.andorp.quicky.IShrink;


public final class CharacterShrink implements IShrink<Character> {
    
    // @Override
    public Enumeration<Character> shrink(Object value) {
        if(!(value instanceof Character)) {
            throw new RuntimeException("CharacterShrink.shrink: invalid object type");
        }
        //TODO:Implement
        return null;
    }
    
}

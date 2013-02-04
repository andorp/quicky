package org.andorp.quicky.engine;

import org.andorp.quicky.engine.ITerminal;

public final class NullTerminal implements ITerminal {
    
    public void putPart(String text) {
    }
    
    public String output() {
        return "";
    }
    
}

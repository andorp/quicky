package org.andorp.quicky.engine;

public final class SystemTerminal implements ITerminal {
    
    private final StringBuilder builder;
    
    public SystemTerminal() {
        builder = new StringBuilder();
    }
    
    public void putPart(String text) {
        builder.append(text);
    }
    
    public String output() {
        return builder.toString();
    }
    
}

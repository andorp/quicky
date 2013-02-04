package org.andorp.quicky.generator.primitive;

import java.util.List;

import org.andorp.quicky.Choose;
import org.andorp.quicky.IGenerator;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.ListOf;


public final class StringGenerator implements IGenerator<String> {
    
    private final ListOf<Character> charGenerator;
    
    public StringGenerator() {
        // TODO: Create more appropiate string //
        charGenerator = new ListOf<Character>(new Choose<Character>(
            PrimitiveRandomGen.character(),
            'a',
            'z'
        ));
    }
    
    public String next(RandomAndSize random) {
        final List<Character> chars = charGenerator.next(random);
        final char[] cs = new char[chars.size()];
        for(int i = 0; i < cs.length; ++i) {
            cs[i] = chars.get(i);
        }
        return new String(cs);
    }
    
}

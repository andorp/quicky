package org.andorp.quicky.generator.primitive;

import java.util.ArrayList;

import org.andorp.quicky.Choose;
import org.andorp.quicky.IGenerator;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.OneOf;


public final class CharacterGenerator implements IGenerator<Character> {

  private final OneOf<Integer> oneOf;

  public CharacterGenerator() {
    final Choose<Integer> c0_127 = new Choose<Integer>(
      PrimitiveRandomGen.integer(), 0, 127
    );
    final Choose<Integer> c0_255 = new Choose<Integer>(
      PrimitiveRandomGen.integer(), 0, 255
    );
    final Choose<Integer> c0_FFFF = new Choose<Integer>(
      PrimitiveRandomGen.integer(), 0, 0xFFFF
    );
    final ArrayList<IGenerator<Integer>> generators = new ArrayList<IGenerator<Integer>>();
    generators.add(c0_127);
    generators.add(c0_255);
    generators.add(c0_FFFF);
    oneOf = new OneOf<Integer>(generators);
  }

  public Character next(RandomAndSize random) {
    final int x = oneOf.next(random).next(random);
    return new Character((char)x);
  }

}

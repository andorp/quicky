package org.andorp.quicky.generator.primitive;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IRandomGen;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.mutable.MutableChoose;

public final class IntegerGenerator implements IGenerator<Integer> {

  private final MutableChoose<Integer> choose;

  public IntegerGenerator() {
    choose = new MutableChoose<Integer>(
      PrimitiveRandomGen.integer()
    );
  }

  public Integer next(RandomAndSize random) {
    final int size = random.size();
    choose.setLimits(-size, size);
    return choose.next(random);
  }

}

package org.andorp.quicky.generator.primitive;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IRandomGen;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.mutable.MutableChoose;

public final class ShortGenerator implements IGenerator<Short> {

  private final MutableChoose<Short> choose;

  public ShortGenerator() {
    choose = new MutableChoose<Short>(
      PrimitiveRandomGen.shorts()
    );
  }

  public Short next(RandomAndSize random) {
    final int size = random.size();
    final short lower = size >= Short.MAX_VALUE ? Short.MIN_VALUE : (short)(-size);
    final short upper = size >= Short.MAX_VALUE ? Short.MAX_VALUE : (short)size;
    choose.setLimits(lower, upper);
    return choose.next(random);
  }

}

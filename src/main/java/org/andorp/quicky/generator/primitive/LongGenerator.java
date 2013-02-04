package org.andorp.quicky.generator.primitive;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.mutable.MutableChoose;
import org.andorp.quicky.random.LongRandomGen;

public final class LongGenerator implements IGenerator<Long> {

  private final MutableChoose<Long> choose;

  public LongGenerator() {
    choose = new MutableChoose<Long>(
      PrimitiveRandomGen.longs()
    );
  }

  public Long next(RandomAndSize random) {
    final long size = random.size();
    choose.setLimits(-size, size);
    return choose.next(random);
  }

}

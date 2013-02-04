package org.andorp.quicky.generator.primitive;

import org.andorp.quicky.Choose;
import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IRandomGen;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.mutable.MutableChoose;

public final class ByteGenerator implements IGenerator<Byte> {

  private final MutableChoose<Byte> choose;

  public ByteGenerator() {
    choose = new MutableChoose<Byte>(
      PrimitiveRandomGen.bytes()
    );
  }

  public Byte next(RandomAndSize random) {
    final int size = random.size();
    final byte lower = size > Byte.MAX_VALUE ? -(Byte.MAX_VALUE + 1) : (byte)(-size);
    final byte upper = size > Byte.MAX_VALUE ? Byte.MAX_VALUE : (byte)size;
    choose.setLimits(lower, upper);
    return choose.next(random);
  }

}

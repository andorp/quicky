package org.andorp.quicky.generator.primitive;

import java.math.BigDecimal;

import org.andorp.quicky.Choose;
import org.andorp.quicky.IGenerator;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.mutable.MutableChoose;


public final class DoubleGenerator implements IGenerator<Double> {

  private final MutableChoose<Integer> aChoose;
  private final Choose<Integer> bChoose;

  public DoubleGenerator() {
    final int precision = 999999999; //TODO: Check it
    aChoose = new MutableChoose<Integer>(
      PrimitiveRandomGen.integer()
    );
    bChoose = new Choose<Integer>(
      PrimitiveRandomGen.integer(), 1, precision
    );
  }

  public DoubleGenerator(int precision) {
    // TODO: Sanity check, can't be negative //
    aChoose = new MutableChoose<Integer>(
      PrimitiveRandomGen.integer()
    );
    bChoose = new Choose<Integer>(
      PrimitiveRandomGen.integer(), 1, precision
    );
  }

  public Double next(RandomAndSize random) {
    final int n = random.size();
    aChoose.setLimits(-n, n);
    final BigDecimal a = new BigDecimal((double)aChoose.next(random));
    final BigDecimal b = new BigDecimal(1.0 / (double)(bChoose.next(random)));
    return a.add(b).doubleValue();
  }

}

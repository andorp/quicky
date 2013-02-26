package org.andorp.quicky.generator.primitive;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.primitive.LongGenerator;

import java.util.Date;

public final class DateGenerator implements IGenerator<Date> {

  private final LongGenerator longGenerator;

  public DateGenerator() {
    longGenerator = new LongGenerator();
  }

  public Date next(RandomAndSize random) {
    Long l = longGenerator.next(random);
    while(l < 0) {
      l = longGenerator.next(random);
    }
    return new Date(l);
  }

}

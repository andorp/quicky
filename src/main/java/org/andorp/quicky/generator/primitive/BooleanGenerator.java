package org.andorp.quicky.generator.primitive;

import java.util.LinkedList;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.Elements;


public final class BooleanGenerator implements IGenerator<Boolean> {

  private final Elements<Boolean> elements;

  public BooleanGenerator() {
    final LinkedList<Boolean> booleans = new LinkedList<Boolean>();
    booleans.add(Boolean.TRUE);
    booleans.add(Boolean.FALSE);
    elements = new Elements(booleans);
  }

  public Boolean next(RandomAndSize random) {
    return elements.next(random);
  }

}

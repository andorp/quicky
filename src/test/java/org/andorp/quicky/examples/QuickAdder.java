package org.andorp.quicky.examples;

import org.andorp.quicky.Postcondition;
import org.andorp.quicky.annotation.DataGenerator;
import org.andorp.quicky.annotation.Property;
import org.andorp.quicky.generator.primitive.DoubleGenerator;
import org.andorp.quicky.generator.primitive.IntegerGenerator;

public class QuickAdder {
    
    private int adder(int a, int b) {
        return a + b;
    }
    
    private double adder(double a, double b) {
        return a + b;
    }
    
    // @Property(name = "Add integers commutative property")
    public void addProperty(
            @DataGenerator(IntegerGenerator.class) int x,
            @DataGenerator(IntegerGenerator.class) int y
            ) {
        int sum = adder(x,y);
        int sum1 = adder(y,x);
        
        Postcondition.assertTrue(sum == sum1, "Values were different");
    }
    
    @Property(name = "Adding doubles holds the commutative property")
    public void addProperty2(
            @DataGenerator(DoubleGenerator.class) double x,
            @DataGenerator(DoubleGenerator.class) double y
            ) {
        double sum = adder(x,y);
        double sum1 = adder(y,x);
        
        Postcondition.assertTrue(sum == sum1, "Values were different");
    }
}

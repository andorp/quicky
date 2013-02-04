package org.andorp.quicky.examples;

import java.util.Collection;

import org.andorp.quicky.engine.Arguments;
import org.andorp.quicky.plugins.TestNGReporter;
import org.andorp.quicky.testsuite.ConsoleTestReporter;
import org.andorp.quicky.testsuite.ITestReporter;
import org.andorp.quicky.testsuite.TestProperty;
import org.andorp.quicky.testsuite.TestPropertyFactory;
import org.andorp.quicky.testsuite.TestPropertyRunner;
import org.andorp.quicky.testsuite.TestSuiteCreationException;
import org.testng.annotations.Test;


public final class QuickAdderNGTest {
    
    @Test(groups = "STAGE-4")
    public void quickAdder() throws TestSuiteCreationException, InstantiationException, IllegalAccessException {
        
        TestPropertyFactory testPropertyFactory = new TestPropertyFactory();
        
        Collection<TestProperty> prs = testPropertyFactory.testProperties(QuickAdder.class);
        ITestReporter testReporter = new TestNGReporter();
        Arguments args = new Arguments(
                true, false, 100, 100, 0.3
                );
        
        TestPropertyRunner testPropertyRunner = new TestPropertyRunner(testReporter, args);
        
        for(TestProperty p : prs) {
            Object instance = QuickAdder.class.newInstance();
            testPropertyRunner.runTestProperty(p, instance);
        }
    }
    
}

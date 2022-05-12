package ru.otus;


import org.junit.jupiter.api.Test;

public class TestRunnerTest {
    @Test
    public void simpleTest() throws Exception {
        (new TestRunner()).runTests("ru.otus.testClasses.SimpleTest");
    }

    @Test
    public void beforeFails() throws Exception {
        (new TestRunner()).runTests("ru.otus.testClasses.TestBeforeFails");
    }

    @Test
    public void instaniationFailed() throws Exception {
        (new TestRunner()).runTests("ru.otus.testClasses.TestInstantiationFails");
    }

    @Test
    public void afterFails() throws Exception {
        (new TestRunner()).runTests("ru.otus.testClasses.TestAfterFails");
    }
}

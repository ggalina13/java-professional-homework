package ru.otus.testClasses;

import org.junit.jupiter.api.Assertions;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestBeforeFails {
    @Before
    public void prepareFails() {
        System.out.println("Preparing for the next test");
        throw new ArithmeticException("Fake exception");
    }

    @Test
    public void correctTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void uncorrectTest() {
        Assertions.assertEquals(1, 2);
    }

    @Test
    public void exceptionTest() {
        throw new ArithmeticException("Fake exception");
    }

    @After
    public void finish() {
        System.out.println("Finishing after tests");
    }
}

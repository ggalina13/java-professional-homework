package ru.otus.testClasses;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import org.junit.jupiter.api.Assertions;

public class SimpleTest {
    @Before
    public void prepare() {
        System.out.println("Preparing for the next test");
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

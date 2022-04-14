package ru.otus;

import com.google.common.math.IntMath;

public class HelloOtus {
    public static void main(String... args) {
        int result = IntMath.binomial(6, 3);
        System.out.println(result);
    }
}

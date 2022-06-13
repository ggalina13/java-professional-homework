package ru.otus.aop.proxy;

public class Summator implements Calculator {

    @Override
    public long calculation(int a) {
        return a;
    }

    @Override
    public long calculation(int a, int b) {
        return a + b;
    }

    @Override
    public long calculation(int a, int b, String c) {
        return a + b + Integer.parseInt(c);
    }

    @Override
    public long calculation(int a, int b, int c) {
        return a + b + c;
    }
}

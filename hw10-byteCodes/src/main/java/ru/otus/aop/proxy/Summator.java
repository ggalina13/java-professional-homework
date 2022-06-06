package ru.otus.aop.proxy;


import ru.otus.Log;

public class Summator implements Calculator {

    @Override
    @Log
    public long calculation(int a) {
        return a;
    }

    @Override
    @Log
    public long calculation(int a, int b) {
        return a + b;
    }

    @Override
    @Log
    public long calculation(int a, int b, String c) {
        return a + b + Integer.parseInt(c);
    }

    @Override
    public long calculation(int a, int b, int c) {
        return a + b + c;
    }
}

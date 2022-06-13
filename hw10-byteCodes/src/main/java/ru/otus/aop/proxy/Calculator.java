package ru.otus.aop.proxy;

import ru.otus.Log;

public interface Calculator {
    @Log
    long calculation(int a);

    @Log
    long calculation(int a, int b);

    @Log
    long calculation(int a, int b, String c);

    long calculation(int a, int b, int c);
}

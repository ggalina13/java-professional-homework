package ru.otus.aop.proxy;

public interface Calculator {
    long calculation(int a);

    long calculation(int a, int b);

    long calculation(int a, int b, String c);

    long calculation(int a, int b, int c);
}

package ru.otus.aop.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Calculator summator = Ioc.createSummator();
        System.out.println(summator.calculation(5));
        System.out.println(summator.calculation(2, 3));
        System.out.println(summator.calculation(2, 3, "10"));
        System.out.println(summator.calculation(2, 3, 10));
    }
}




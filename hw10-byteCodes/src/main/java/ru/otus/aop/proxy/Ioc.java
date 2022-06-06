package ru.otus.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

import ru.otus.Log;

class Ioc {

    private Ioc() {
    }

    static Calculator createSummator() {
        InvocationHandler handler = new DemoInvocationHandler(new Summator());
        return (Calculator) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
            new Class<?>[]{Calculator.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Calculator myClass;

        DemoInvocationHandler(Calculator myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Log.class)) {
                StringBuilder message = new StringBuilder();
                message.append("invoking method: ");
                message.append(method.getName()).append(" ");
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i].isNamePresent()) {
                        message.append(parameters[i].getName()).append(":").append(args[i]);
                    } else {
                        message.append("param").append(i + 1).append(":").append(args[i]);
                    }
                    if (i < parameters.length - 1) {
                        message.append(", ");
                    }
                }
                System.out.println(message);
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                "myClass=" + myClass +
                '}';
        }
    }
}

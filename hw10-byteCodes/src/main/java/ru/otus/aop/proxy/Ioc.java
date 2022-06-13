package ru.otus.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        private final HashMap<Method, MethodInfo> methodsInfo = new HashMap<>();

        DemoInvocationHandler(Calculator myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!methodsInfo.containsKey(method)) {
                methodsInfo.put(method, new MethodInfo(method.isAnnotationPresent(Log.class),
                    method.getName(),
                    Arrays.stream(method.getParameters())
                        .map(x -> x.isNamePresent() ? x.getName() : "param")
                        .collect(Collectors.toList())));
            }
            MethodInfo methodInfo = methodsInfo.get(method);
            if (methodInfo.isLogged()) {
                StringBuilder message = new StringBuilder();
                message.append("invoking method: ");
                message.append(methodInfo.getName()).append(" ");
                List<String> parameters = methodInfo.getParameters();
                for (int i = 0; i < parameters.size(); i++) {
                    message.append(parameters.get(i)).append(i + 1).append(":").append(args[i]);
                    if (i < parameters.size() - 1) {
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

package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestRunner {
    public void runTests(String className) throws ClassNotFoundException {
        Class<?> testClass = Class.forName(className);
        Method[] methodsPublic = testClass.getMethods();
        int testsPassed = 0;
        Set<Method> befores = new HashSet<>();
        Set<Method> afters = new HashSet<>();
        Set<Method> tests = new HashSet<>();
        for (Method method : methodsPublic) {
            if (method.isAnnotationPresent(Before.class)) {
                befores.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                afters.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                tests.add(method);
            }
        }
        for (Method test : tests) {
            boolean testPassed = runTest(test, testClass, befores, afters);
            if (testPassed) {
                testsPassed++;
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Total: " + tests.size() + "; Passed: " + testsPassed + "; Failed: " + (tests.size() - testsPassed));
    }

    private boolean runTest(Method test, Class<?> testClass, Set<Method> befores, Set<Method> afters) {
        System.out.println("Starting test: " + test.getName());
        boolean testPassed = true;
        try {
            Object testClassInstance = createInstance(testClass);
            try {
                prepare(befores, testClassInstance);
                try {
                    test.invoke(testClassInstance);
                } catch (InvocationTargetException e) {
                    System.out.println("Failed: exception occured in testing method " + e.getTargetException());
                    testPassed = false;
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    System.out.println("Failed: failed to execute test method" + e);
                    testPassed = false;
                }
            } catch (InvocationTargetException e) {
                System.out.println("Failed: exception occured in before method " + e.getTargetException());
                testPassed = false;
            } catch (IllegalAccessException e) {
                System.out.println("Failed: failed to run before method" + e);
                testPassed = false;
            } finally {
                try {
                    finish(afters, testClassInstance);
                } catch (InvocationTargetException e) {
                    System.out.println("Failed: exception occured in after method " + e.getTargetException());
                    testPassed = false;
                } catch (IllegalAccessException e) {
                    System.out.println("Failed: failed to run after method " + e);
                    testPassed = false;
                }
            }
        } catch (ReflectiveOperationException e) {
            System.out.println("Failed: failed to create a test class instance " + e);
            testPassed = false;
        }
        if (testPassed) {
            System.out.println("OK");
        }
        return testPassed;
    }

    private Object createInstance(Class<?> testClass) throws InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
        Constructor<?> constructor = testClass.getConstructor();
        return constructor.newInstance();
    }

    private void prepare(Set<Method> befores, Object testClassInstance) throws InvocationTargetException,
            IllegalAccessException {
        for (Method before : befores) {
            before.invoke(testClassInstance);
        }
    }

    private void finish(Set<Method> afters, Object testClassInstance) throws InvocationTargetException,
            IllegalAccessException {
        for (Method after : afters) {
            after.invoke(testClassInstance);
        }
    }
}

package ru.otus.aop.proxy;

import java.util.List;

public class MethodInfo {
    private final boolean logged;
    private final String name;
    private final List<String> paramNames;

    public MethodInfo(boolean logged, String name, List<String> parameters) {
        this.logged = logged;
        this.name = name;
        this.paramNames = parameters;
    }

    public boolean isLogged() {
        return logged;
    }

    public String getName() {
        return name;
    }

    public List<String> getParameters() {
        return paramNames;
    }
}

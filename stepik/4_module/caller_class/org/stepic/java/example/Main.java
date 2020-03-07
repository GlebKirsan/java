package org.stepic.java.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        String classCaller = null;
        StackTraceElement[] stack = new Exception().getStackTrace();
        if (stack.length != 2) {
            classCaller = stack[1].getClassName() + "#" + stack[2].getMethodName();
        }
        return classCaller;
    }
}
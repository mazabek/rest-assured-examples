package com.testinglaboratory.testingbasics.examples;

public class TheIncrementor {

    public static Integer getValue() {
        return value;
    }

    public static void setValue(Integer value) {
        TheIncrementor.value = value;
    }

    public static void increment() {
        value++;
    }

    private static Integer value = 0;

}

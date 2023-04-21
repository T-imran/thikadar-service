package com.example.task.service;

public class TestCode {

    public static void main(String[] args) {
        System.out.println(divide(1,3));
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }
}

package com.patiun.informationhandling.logic.expressioncalculation;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {

    private final Deque<Double> stack = new ArrayDeque<>();

    public void push(Double value) {
        stack.push(value);
    }

    public Double pop() {
        return stack.pop();
    }
}

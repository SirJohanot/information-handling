package com.patiun.informationhandling.logic.expressioncalculation;

public class NonTerminalExpression implements MathExpression {

    private final double number;

    public NonTerminalExpression(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.push(number);
    }
}

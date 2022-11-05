package com.patiun.informationhandling.logic.expressioncalculation;

public class TerminalDivideExpression implements MathExpression {
    @Override
    public void interpret(Context context) {
        Double secondMember = context.pop();
        Double firstMember = context.pop();
        context.push(firstMember / secondMember);
    }
}

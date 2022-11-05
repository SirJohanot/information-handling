package com.patiun.informationhandling.logic.expressioncalculation;

import com.patiun.informationhandling.logic.expressioncalculation.exception.ExpressionCalculationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {

    private static final String LEXEME_DELIMITER = " ";

    private static final Logger LOGGER = LogManager.getLogger(ExpressionCalculator.class);

    private final List<MathExpression> mathExpressionList = new ArrayList<>();

    public Double calculate(String expression, Map<Character, Double> variables) throws ExpressionCalculationException {
        LOGGER.info("Started calculating " + expression + " with variables " + variables.toString());
        mathExpressionList.clear();
        expression = expression.replaceAll("[\\[\\]]", "");
        for (String lexeme : expression.split(LEXEME_DELIMITER)) {
            if (lexeme.isEmpty()) {
                continue;
            }
            switch (lexeme.charAt(0)) {
                case '+':
                    if (lexeme.length() == 1) {
                        mathExpressionList.add(new TerminalPlusExpression());
                    } else {
                        addValueToExpression(lexeme, variables);
                    }
                    break;
                case '-':
                    if (lexeme.length() == 1) {
                        mathExpressionList.add(new TerminalMinusExpression());
                    } else {
                        addValueToExpression(lexeme, variables);
                    }
                    break;
                case '*':
                    mathExpressionList.add(new TerminalMultiplyExpression());
                    break;
                case '/':
                    mathExpressionList.add(new TerminalDivideExpression());
                    break;
                default:
                    addValueToExpression(lexeme, variables);
            }
        }
        Double finalValue = finaliseCalculation();
        LOGGER.info("Calculated " + expression + " with variables " + variables + "\nAnswer: " + finalValue);
        return finalValue;
    }

    private void addValueToExpression(String lexeme, Map<Character, Double> variables) throws ExpressionCalculationException {
        Scanner scanner = new Scanner(lexeme);
        if (scanner.hasNextDouble()) {
            mathExpressionList.add(new NonTerminalExpression(scanner.nextDouble()));
        } else {
            Double value = variables.get(lexeme.charAt(0));
            if (value != null) {
                mathExpressionList.add(new NonTerminalExpression(value));
            } else {
                ExpressionCalculationException expressionCalculationException = new ExpressionCalculationException("Could not calculate expression: variable of unknown value encountered");
                LOGGER.throwing(expressionCalculationException);
                throw expressionCalculationException;
            }
        }
    }

    private Double finaliseCalculation() {
        Context context = new Context();
        for (MathExpression expression : mathExpressionList) {
            expression.interpret(context);
        }
        return context.pop();
    }
}

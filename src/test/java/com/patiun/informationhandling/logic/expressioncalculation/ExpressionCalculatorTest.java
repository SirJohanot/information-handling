package com.patiun.informationhandling.logic.expressioncalculation;

import com.patiun.informationhandling.logic.expressioncalculation.exception.ExpressionCalculationException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExpressionCalculatorTest {

    private static final String ADDITION_EXPRESSION = "[ 17 9 + ]";  //(2+2)*2
    private static final Double ANSWER_TO_ADDITION = 26.0;
    private static final String SUBTRACTION_EXPRESSION = "[ 12 6 - ]";  //(2+2)*2
    private static final Double ANSWER_TO_SUBTRACTION = 6.0;
    private static final String POSITIVE_MULTIPLICATION_EXPRESSION = "[ 5 6 * ]";  //(2+2)*2
    private static final Double ANSWER_TO_POSITIVE_MULTIPLICATION = 30.0;
    private static final String NEGATIVE_MULTIPLICATION_EXPRESSION = "[ -45 2 * ]";  //(2+2)*2
    private static final Double ANSWER_TO_NEGATIVE_MULTIPLICATION = -90.0;
    private static final String POSITIVE_DIVISION_EXPRESSION = "[ 18 9 / ]";  //(2+2)*2
    private static final Double ANSWER_TO_POSITIVE_DIVISION = 2.0;
    private static final String NEGATIVE_DIVISION_EXPRESSION = "[ -25 5 / ]";  //(2+2)*2
    private static final Double ANSWER_TO_NEGATIVE_DIVISION = -5.0;
    private static final String ADVANCED_EXPRESSION = "[ 8 2 5 * + 1 3 2 * + 4 - /]";  //(8+2*5)/(1+3*2-4)
    private static final Double ANSWER_TO_ADVANCED_EXPRESSION = 6.0;
    private static final Map<Character, Double> EMPTY_EXPRESSION_MAP = new HashMap<>();

    private static final String EXPRESSION_WITH_VARIABLES = "[ x y + 3 /]";
    private static final Map<Character, Double> VARIABLES_MAP = new HashMap<>();

    static {
        VARIABLES_MAP.put('x', 1.0);
        VARIABLES_MAP.put('y', 2.0);
    }

    private static final Double ANSWER_TO_EXPRESSION_WITH_VARIABLES = 1.0;

    private static final String EXPRESSION_WITH_UNKNOWN_VARIABLES = "[ a 2 + c / ]";

    private static final double ACCEPTABLE_ASSERTION_DELTA = 0.001;

    private final ExpressionCalculator expressionCalculator = new ExpressionCalculator();

    @Test
    public void testCalculateShouldReturnCalculateAdditionExpression() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(ADDITION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_ADDITION, actualAnswer, ACCEPTABLE_ASSERTION_DELTA);
    }

    @Test
    public void testCalculateShouldCalculateSubtractionExpression() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(SUBTRACTION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_SUBTRACTION, actualAnswer, ACCEPTABLE_ASSERTION_DELTA);
    }

    @Test
    public void testCalculateShouldCalculatePositiveMultiplicationExpression() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(POSITIVE_MULTIPLICATION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_POSITIVE_MULTIPLICATION, actualAnswer, ACCEPTABLE_ASSERTION_DELTA);
    }

    @Test
    public void testCalculateShouldCalculateNegativeMultiplicationExpression() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(NEGATIVE_MULTIPLICATION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_NEGATIVE_MULTIPLICATION, actualAnswer, ACCEPTABLE_ASSERTION_DELTA);
    }

    @Test
    public void testCalculateShouldCalculatePositiveDivisionExpression() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(POSITIVE_DIVISION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_POSITIVE_DIVISION, actualAnswer, ACCEPTABLE_ASSERTION_DELTA);
    }

    @Test
    public void testCalculateShouldCalculateNegativeDivisionExpression() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(NEGATIVE_DIVISION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_NEGATIVE_DIVISION, actualAnswer, ACCEPTABLE_ASSERTION_DELTA);
    }

    @Test
    public void testCalculateShouldReturnTheRightAnswerForAnAdvancedExpression() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(ADVANCED_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_ADVANCED_EXPRESSION, actualAnswer, ACCEPTABLE_ASSERTION_DELTA);
    }

    @Test
    public void testCalculateShouldCalculateExpressionWithVariables() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(EXPRESSION_WITH_VARIABLES, VARIABLES_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_EXPRESSION_WITH_VARIABLES, actualAnswer, ACCEPTABLE_ASSERTION_DELTA);
    }

    @Test(expected = ExpressionCalculationException.class)
    public void testCalculateShouldThrowExpressionCalculationExceptionWhenDoesNotKnowAVariablesValue() throws ExpressionCalculationException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(EXPRESSION_WITH_UNKNOWN_VARIABLES, EMPTY_EXPRESSION_MAP);
        //then
    }
}

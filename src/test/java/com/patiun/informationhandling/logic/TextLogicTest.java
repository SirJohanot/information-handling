package com.patiun.informationhandling.logic;

import com.patiun.informationhandling.component.Composite;
import com.patiun.informationhandling.component.Lexeme;
import com.patiun.informationhandling.logic.exception.UnsupportedComponentTypeException;
import com.patiun.informationhandling.logic.expressioncalculation.ExpressionCalculator;
import com.patiun.informationhandling.logic.expressioncalculation.exception.ExpressionCalculationException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TextLogicTest {

    private static final String FIRST_EXPRESSION = "[ 2 1 + x / 2 + ]";
    private static final Double ANSWER_TO_FIRST_EXPRESSION = 3.0;
    private static final String ANSWER_TO_FIRST_EXPRESSION_STRING = Double.toString(ANSWER_TO_FIRST_EXPRESSION);
    private static final String SECOND_EXPRESSION = "[ 2 2 + y * ]";
    private static final Double ANSWER_TO_SECOND_EXPRESSION = 8.0;
    private static final String ANSWER_TO_SECOND_EXPRESSION_STRING = Double.toString(ANSWER_TO_SECOND_EXPRESSION);
    private static final Map<Character, Double> EXPRESSION_VARIABLES_VALUES = new HashMap<>();

    static {
        EXPRESSION_VARIABLES_VALUES.put('x', 3.0);
        EXPRESSION_VARIABLES_VALUES.put('y', 2.0);
    }

    private static final Composite FIRST_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("was"),
            Lexeme.word("a"),
            Lexeme.word("sunny.")));
    private static final Composite SECOND_SENTENCE_WITH_EXPRESSION_COMPOSITE = new Composite(Arrays.asList(Lexeme.expression(FIRST_EXPRESSION),
            Lexeme.word("Day!")));
    private static final Composite THIRD_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Never"),
            Lexeme.word("before"),
            Lexeme.word("seen.")));
    private static final Composite FOURTH_SENTENCE_WITH_EXPRESSION_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Saint"),
            Lexeme.expression(SECOND_EXPRESSION),
            Lexeme.word("Petersburg?")));
    private static final Composite FIRST_PARAGRAPH_COMPOSITE = new Composite(Collections.singletonList(FIRST_SENTENCE_COMPOSITE));
    private static final Composite SECOND_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE = new Composite(Arrays.asList(SECOND_SENTENCE_WITH_EXPRESSION_COMPOSITE, THIRD_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE = new Composite(Collections.singletonList(FOURTH_SENTENCE_WITH_EXPRESSION_COMPOSITE));
    private static final Composite TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, SECOND_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE, THIRD_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE));
    private static final String EXPECTED_TEXT_OF_COMPOSITE = "It was a sunny.\n[ 2 1 + x / 2 + ] Day! Never before seen.\nSaint [ 2 2 + y * ] Petersburg?";
    private static final Composite EXPECTED_TEXT_COMPOSITE_WITH_SORTED_PARAGRAPHS = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, THIRD_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE, SECOND_PARAGRAPH_WITH_EXPRESSIONS_COMPOSITE));

    private static final Composite SECOND_SENTENCE_WITH_CALCULATED_EXPRESSION_COMPOSITE = new Composite(Arrays.asList(Lexeme.word(ANSWER_TO_FIRST_EXPRESSION_STRING),
            Lexeme.word("Day!")));
    private static final Composite FOURTH_SENTENCE_WITH_CALCULATED_EXPRESSION_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Saint"),
            Lexeme.word(ANSWER_TO_SECOND_EXPRESSION_STRING),
            Lexeme.word("Petersburg?")));
    private static final Composite SECOND_PARAGRAPH_WITH_CALCULATED_EXPRESSIONS_COMPOSITE = new Composite(Arrays.asList(SECOND_SENTENCE_WITH_CALCULATED_EXPRESSION_COMPOSITE, THIRD_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_WITH_CALCULATED_EXPRESSIONS_COMPOSITE = new Composite(Collections.singletonList(FOURTH_SENTENCE_WITH_CALCULATED_EXPRESSION_COMPOSITE));
    private static final Composite EXPECTED_CALCULATED_TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, SECOND_PARAGRAPH_WITH_CALCULATED_EXPRESSIONS_COMPOSITE, THIRD_PARAGRAPH_WITH_CALCULATED_EXPRESSIONS_COMPOSITE));

    private final TextLogic textLogic = new TextLogic();

    @Test
    public void testParsedTextToStringShouldReturnAStringValueEqualToTheTextBeforeParsing() throws UnsupportedComponentTypeException {
        //given
        //when
        String actualTextOfComposite = textLogic.parsedTextToString(TEXT_COMPOSITE);
        //then
        Assert.assertEquals(EXPECTED_TEXT_OF_COMPOSITE, actualTextOfComposite);
    }

    @Test
    public void testSortParagraphsBySentenceNumberShouldProperlySortMultipleParagraphs() {
        //given
        //when
        Composite actualTextWithSortedParagraphs = textLogic.sortParagraphsBySentenceNumber(TEXT_COMPOSITE);
        //then
        Assert.assertEquals(EXPECTED_TEXT_COMPOSITE_WITH_SORTED_PARAGRAPHS, actualTextWithSortedParagraphs);
    }

    @Test
    public void testCalculateExpressionsInTextShouldCalculateIfThereAreMultipleExpressionsInDifferentParagraphs() throws UnsupportedComponentTypeException, ExpressionCalculationException {
        //given
        ExpressionCalculator expressionCalculatorMock = Mockito.mock(ExpressionCalculator.class);
        Mockito.when(expressionCalculatorMock.calculate(FIRST_EXPRESSION, EXPRESSION_VARIABLES_VALUES)).thenReturn(ANSWER_TO_FIRST_EXPRESSION);
        Mockito.when(expressionCalculatorMock.calculate(SECOND_EXPRESSION, EXPRESSION_VARIABLES_VALUES)).thenReturn(ANSWER_TO_SECOND_EXPRESSION);
        TextLogic localTextLogic = new TextLogic(expressionCalculatorMock);
        //when
        Composite actualCalculatedTextComposite = localTextLogic.calculateExpressionsInText(TEXT_COMPOSITE, EXPRESSION_VARIABLES_VALUES);
        //then
        Assert.assertEquals(EXPECTED_CALCULATED_TEXT_COMPOSITE, actualCalculatedTextComposite);
    }
}

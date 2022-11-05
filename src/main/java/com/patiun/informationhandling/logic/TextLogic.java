package com.patiun.informationhandling.logic;

import com.patiun.informationhandling.component.Component;
import com.patiun.informationhandling.component.Composite;
import com.patiun.informationhandling.component.Lexeme;
import com.patiun.informationhandling.component.LexemeType;
import com.patiun.informationhandling.component.parsing.Parser;
import com.patiun.informationhandling.component.parsing.TextParserBuilder;
import com.patiun.informationhandling.logic.comparator.ChildComponentsComparator;
import com.patiun.informationhandling.logic.exception.UnsupportedComponentTypeException;
import com.patiun.informationhandling.logic.expressioncalculation.ExpressionCalculator;
import com.patiun.informationhandling.logic.expressioncalculation.exception.ExpressionCalculationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextLogic {

    private static final String PARAGRAPHS_DELIMITER = "\n";
    private static final String LEXEMES_AND_SENTENCES_DELIMITER = " ";

    private static final Logger LOGGER = LogManager.getLogger(TextLogic.class);

    private final ExpressionCalculator expressionCalculator;

    public TextLogic() {
        expressionCalculator = new ExpressionCalculator();
    }

    public TextLogic(ExpressionCalculator expressionCalculator) {
        this.expressionCalculator = expressionCalculator;
    }

    public Composite parse(String text) {
        TextParserBuilder builder = new TextParserBuilder();
        Parser parser = builder.build();
        return parser.parse(text);
    }

    public String parsedTextToString(Composite text) throws UnsupportedComponentTypeException {
        return parsedComponentToString(text, PARAGRAPHS_DELIMITER);
    }

    private String parsedComponentToString(Component text, String componentDelimiter) throws UnsupportedComponentTypeException {
        StringBuilder resultString = new StringBuilder();
        if (text.getClass() == Composite.class) {
            Composite textAsComposite = (Composite) text;
            List<Component> textAsCompositeComponents = textAsComposite.getChildren();
            for (Component component : textAsCompositeComponents) {
                resultString.append(parsedComponentToString(component, LEXEMES_AND_SENTENCES_DELIMITER));
                if (textAsCompositeComponents.indexOf(component) != (textAsCompositeComponents.size() - 1)) {
                    resultString.append(componentDelimiter);
                }
            }
            return resultString.toString();
        }
        if (text.getClass() == Lexeme.class) {
            Lexeme textAsLexeme = (Lexeme) text;
            String lexemeText = textAsLexeme.getValue();
            resultString.append(lexemeText);
            return resultString.toString();
        }
        UnsupportedComponentTypeException unsupportedComponentTypeException =
                new UnsupportedComponentTypeException("An unsupported Component was passed to method");
        LOGGER.throwing(unsupportedComponentTypeException);
        throw unsupportedComponentTypeException;
    }

    public Composite calculateExpressionsInText(Composite text, Map<Character, Double> variables) throws UnsupportedComponentTypeException {
        List<Component> calculatedTextComponents = new ArrayList<>();
        List<Component> textComponents = text.getChildren();
        for (Component component : textComponents) {
            Component calculatedComponent;
            try {
                calculatedComponent = calculateExpressionsInComponent(component, variables);
            } catch (UnsupportedComponentTypeException | ExpressionCalculationException e) {
                throw new UnsupportedComponentTypeException(e);
            }
            calculatedTextComponents.add(calculatedComponent);
        }
        return new Composite(calculatedTextComponents);
    }

    private Component calculateExpressionsInComponent(Component component, Map<Character, Double> variables) throws UnsupportedComponentTypeException, ExpressionCalculationException {
        if (component.getClass() == Composite.class) {
            Composite composite = (Composite) component;
            List<Component> calculatedComponents = new ArrayList<>();
            List<Component> components = composite.getChildren();
            for (Component componentIterator : components) {
                Component calculatedComponentIterator = calculateExpressionsInComponent(componentIterator, variables);
                calculatedComponents.add(calculatedComponentIterator);
            }
            return new Composite(calculatedComponents);
        }
        if (component.getClass() == Lexeme.class) {
            Lexeme lexeme = (Lexeme) component;
            String lexemeValue = lexeme.getValue();
            String calculatedValue;
            if (lexeme.getLexemeType() == LexemeType.EXPRESSION) {
                calculatedValue = Double.toString(expressionCalculator.calculate(lexemeValue, variables));
            } else {
                calculatedValue = lexemeValue;
            }
            return Lexeme.word(calculatedValue);
        }
        UnsupportedComponentTypeException unsupportedComponentTypeException =
                new UnsupportedComponentTypeException("An unsupported Component was passed to method");
        LOGGER.throwing(unsupportedComponentTypeException);
        throw unsupportedComponentTypeException;
    }

    public Composite sortParagraphsBySentenceNumber(Composite text) {
        List<Composite> paragraphs = new ArrayList<>();
        for (Component component : text.getChildren()) {
            paragraphs.add((Composite) component);
        }
        paragraphs.sort(new ChildComponentsComparator());
        return new Composite(paragraphs);
    }

}

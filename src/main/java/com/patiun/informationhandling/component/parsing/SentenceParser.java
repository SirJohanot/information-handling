package com.patiun.informationhandling.component.parsing;

import com.patiun.informationhandling.component.Composite;
import com.patiun.informationhandling.component.Lexeme;

public class SentenceParser extends AbstractParser {

    private static final String LEXEME_TYPES_DELIMITER = "(?:\\s(?=\\[)|(?<=\\])\\s)";
    private static final String WORDS_DELIMITER = "\\s";

    public SentenceParser() {
        super();
    }

    @Override
    public Composite parse(String text) {
        Composite composite = new Composite();
        String[] lexemes = text.split(LEXEME_TYPES_DELIMITER);
        for (String lexeme : lexemes) {
            if (lexeme.contains("[")) {
                composite.add(Lexeme.expression(lexeme));
            } else {
                parseWordsToComposite(lexeme, composite);
            }
        }
        return composite;
    }

    private void parseWordsToComposite(String words, Composite composite) {
        String[] splitWords = words.split(WORDS_DELIMITER);
        for (String word : splitWords) {
            composite.add(Lexeme.word(word));
        }
    }
}

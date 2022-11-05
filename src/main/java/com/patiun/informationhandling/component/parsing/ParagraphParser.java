package com.patiun.informationhandling.component.parsing;

import com.patiun.informationhandling.component.Composite;

public class ParagraphParser extends AbstractParser {

    private static final String SENTENCE_DELIMITER_REGEX = "(?<=[\\.\\?\\![\\.]{3}])\\s";

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    public Composite parse(String text) {
        return templateParse(text, SENTENCE_DELIMITER_REGEX);
    }
}

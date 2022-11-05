package com.patiun.informationhandling.component.parsing;

import com.patiun.informationhandling.component.Composite;

public class TextParser extends AbstractParser {

    private static final String PARAGRAPH_DELIMITER = "\n";

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Composite parse(String text) {
        return templateParse(text, PARAGRAPH_DELIMITER);
    }
}

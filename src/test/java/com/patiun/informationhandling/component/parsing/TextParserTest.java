package com.patiun.informationhandling.component.parsing;

import com.patiun.informationhandling.component.Composite;
import com.patiun.informationhandling.component.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

public class TextParserTest {

    private static final String FIRST_PARAGRAPH = "It was a sunny. Day!";
    private static final String SECOND_PARAGRAPH = "Never before seen.";
    private static final String THIRD_PARAGRAPH = "Saint Petersburg?";
    private static final String TEXT_TO_PARSE = FIRST_PARAGRAPH + "\n" + SECOND_PARAGRAPH + "\n" + THIRD_PARAGRAPH;
    private static final Composite FIRST_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("was"),
            Lexeme.word("a"),
            Lexeme.word("sunny.")));
    private static final Composite SECOND_SENTENCE_COMPOSITE = new Composite(Collections.singletonList(Lexeme.word("Day!")));
    private static final Composite THIRD_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Never"),
            Lexeme.word("before"),
            Lexeme.word("seen.")));
    private static final Composite FOURTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Saint"),
            Lexeme.word("Petersburg?")));
    private static final Composite FIRST_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(FIRST_SENTENCE_COMPOSITE, SECOND_SENTENCE_COMPOSITE));
    private static final Composite SECOND_PARAGRAPH_COMPOSITE = new Composite(Collections.singletonList(THIRD_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_COMPOSITE = new Composite(Collections.singletonList(FOURTH_SENTENCE_COMPOSITE));
    private static final Composite EXPECTED_TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, SECOND_PARAGRAPH_COMPOSITE, THIRD_PARAGRAPH_COMPOSITE));

    @Test
    public void testParseShouldProperlyParseTextWithMultipleParagraphs() {
        //given
        ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
        Mockito.when(paragraphParser.parse(FIRST_PARAGRAPH)).thenReturn(FIRST_PARAGRAPH_COMPOSITE);
        Mockito.when(paragraphParser.parse(SECOND_PARAGRAPH)).thenReturn(SECOND_PARAGRAPH_COMPOSITE);
        Mockito.when(paragraphParser.parse(THIRD_PARAGRAPH)).thenReturn(THIRD_PARAGRAPH_COMPOSITE);
        TextParser textParser = new TextParser(paragraphParser);
        //when
        Composite actualTextComposite = textParser.parse(TEXT_TO_PARSE);
        //then
        Assert.assertEquals(EXPECTED_TEXT_COMPOSITE, actualTextComposite);
    }

}

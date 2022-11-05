package com.patiun.informationhandling.component.parsing;

import com.patiun.informationhandling.component.Composite;
import com.patiun.informationhandling.component.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

public class ParagraphParserTest {

    private static final String FIRST_SENTENCE = "It was a sunny...";
    private static final String SECOND_SENTENCE = "Day!";
    private static final String THIRD_SENTENCE = "Never before seen.";
    private static final String FOURTH_SENTENCE = "Saint Petersburg?";
    private static final String PARAGRAPH_TO_PARSE = FIRST_SENTENCE + " " + SECOND_SENTENCE + " " + THIRD_SENTENCE + " " + FOURTH_SENTENCE;
    private static final Composite FIRST_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("was"),
            Lexeme.word("a"),
            Lexeme.word("sunny...")));
    private static final Composite SECOND_SENTENCE_COMPOSITE = new Composite(Collections.singletonList(Lexeme.word("Day!")));
    private static final Composite THIRD_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Never"),
            Lexeme.word("before"),
            Lexeme.word("seen.")));
    private static final Composite FOURTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Saint"),
            Lexeme.word("Petersburg?")));
    private static final Composite EXPECTED_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(FIRST_SENTENCE_COMPOSITE, SECOND_SENTENCE_COMPOSITE, THIRD_SENTENCE_COMPOSITE, FOURTH_SENTENCE_COMPOSITE));

    @Test
    public void testParseShouldProperlyParseAParagraphWithMultipleSentencesThatEndWithDifferentSymbols() {
        //given
        SentenceParser sentenceParser = Mockito.mock(SentenceParser.class);
        Mockito.when(sentenceParser.parse(FIRST_SENTENCE)).thenReturn(FIRST_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(SECOND_SENTENCE)).thenReturn(SECOND_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(THIRD_SENTENCE)).thenReturn(THIRD_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(FOURTH_SENTENCE)).thenReturn(FOURTH_SENTENCE_COMPOSITE);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        //when
        Composite actualParagraphComposite = paragraphParser.parse(PARAGRAPH_TO_PARSE);
        //then
        Assert.assertEquals(EXPECTED_PARAGRAPH_COMPOSITE, actualParagraphComposite);
    }

}

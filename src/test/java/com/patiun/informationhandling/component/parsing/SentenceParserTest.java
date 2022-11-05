package com.patiun.informationhandling.component.parsing;

import com.patiun.informationhandling.component.Composite;
import com.patiun.informationhandling.component.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentenceParserTest {

    private static final String SENTENCE_TO_PARSE = "It was a [ 5 6 + 11 / ] sunny day never before seen Saint Petersburg?";
    private static final Composite EXPECTED_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("was"),
            Lexeme.word("a"),
            Lexeme.expression("[ 5 6 + 11 / ]"),
            Lexeme.word("sunny"),
            Lexeme.word("day"),
            Lexeme.word("never"),
            Lexeme.word("before"),
            Lexeme.word("seen"),
            Lexeme.word("Saint"),
            Lexeme.word("Petersburg?")));

    private final SentenceParser sentenceParser = new SentenceParser();

    @Test
    public void testParseShouldParseASentenceWithBothExpressionsAndWords() {
        //given
        //when
        Composite actualSentenceComposite = sentenceParser.parse(SENTENCE_TO_PARSE);
        //then
        Assert.assertEquals(EXPECTED_SENTENCE_COMPOSITE, actualSentenceComposite);
    }

}

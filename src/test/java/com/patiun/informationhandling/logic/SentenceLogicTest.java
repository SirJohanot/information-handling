package com.patiun.informationhandling.logic;

import com.patiun.informationhandling.component.Composite;
import com.patiun.informationhandling.component.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentenceLogicTest {

    private static final char FIRST_CHARACTER_TO_SORT_LEXEMES_BY = 'e';
    private static final Composite SENTENCE_COMPOSITE_TO_SORT_BY_FIRST_CHARACTER = new Composite(Arrays.asList(Lexeme.word("nevermore"),
            Lexeme.word("evening"),
            Lexeme.word("early"),
            Lexeme.expression("will")));
    private static final Composite EXPECTED_SENTENCE_COMPOSITE_SORTED_BY_FIRST_CHARACTER = new Composite(Arrays.asList(Lexeme.expression("will"),
            Lexeme.word("early"),
            Lexeme.word("evening"),
            Lexeme.word("nevermore")));

    private final SentenceLogic sentenceLogic = new SentenceLogic();

    @Test
    public void testSortWordsByTargetCharacterNumberShouldSortInSentencesWithBothWordsAndExpressions() {
        //given
        //when
        Composite actualSentenceSortedByFirstCharacter = sentenceLogic.sortWordsByTargetCharacterNumber(SENTENCE_COMPOSITE_TO_SORT_BY_FIRST_CHARACTER, FIRST_CHARACTER_TO_SORT_LEXEMES_BY);
        //then
        Assert.assertEquals(EXPECTED_SENTENCE_COMPOSITE_SORTED_BY_FIRST_CHARACTER, actualSentenceSortedByFirstCharacter);
    }
}

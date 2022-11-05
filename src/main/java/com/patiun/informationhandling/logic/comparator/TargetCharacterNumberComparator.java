package com.patiun.informationhandling.logic.comparator;

import com.patiun.informationhandling.component.Lexeme;

import java.util.Comparator;

public class TargetCharacterNumberComparator implements Comparator<Lexeme> {

    private final char targetCharacter;

    public TargetCharacterNumberComparator(char targetCharacter) {
        this.targetCharacter = targetCharacter;
    }

    @Override
    public int compare(Lexeme firstLexeme, Lexeme secondLexeme) {
        int targetCharacterNumberDifference = targetCharacterCount(firstLexeme.getValue()) - targetCharacterCount(secondLexeme.getValue());
        if (targetCharacterNumberDifference != 0) {
            return targetCharacterNumberDifference;
        }
        return firstLexeme.getValue().compareToIgnoreCase(secondLexeme.getValue());
    }

    private int targetCharacterCount(String lexeme) {
        int counter = 0;
        for (int i = 0; i < lexeme.length(); i++) {
            if (lexeme.charAt(i) == targetCharacter) {
                counter++;
            }
        }
        return counter;
    }
}

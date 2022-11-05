package com.patiun.informationhandling.logic;

import com.patiun.informationhandling.component.Component;
import com.patiun.informationhandling.component.Composite;
import com.patiun.informationhandling.component.Lexeme;
import com.patiun.informationhandling.logic.comparator.TargetCharacterNumberComparator;

import java.util.ArrayList;
import java.util.List;

public class SentenceLogic {

    public Composite sortWordsByTargetCharacterNumber(Composite sentence, char targetCharacter) {
        List<Lexeme> lexemes = new ArrayList<>();
        for (Component component : sentence.getChildren()) {
            lexemes.add((Lexeme) component);
        }
        lexemes.sort(new TargetCharacterNumberComparator(targetCharacter));
        return new Composite(lexemes);
    }
}

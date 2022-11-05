package com.patiun.informationhandling.component;

public class Lexeme implements Component {

    private final String value;
    private final LexemeType lexemeType;

    public Lexeme(String value, LexemeType lexemeType) {
        this.value = value;
        this.lexemeType = lexemeType;
    }

    public static Lexeme word(String value) {
        return new Lexeme(value, LexemeType.WORD);
    }

    public static Lexeme expression(String value) {
        return new Lexeme(value, LexemeType.EXPRESSION);
    }

    public String getValue() {
        return value;
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lexeme lexeme = (Lexeme) o;
        if (value != null ? !value.equals(lexeme.value) : lexeme.value != null) {
            return false;
        }
        return lexemeType == lexeme.lexemeType;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (lexemeType != null ? lexemeType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "value='" + value + '\'' +
                ", lexemeType=" + lexemeType +
                '}';
    }
}

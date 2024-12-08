package org.dersbian.vandiorsp.model;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class LexerUtility {
    public static final List<Character> OPERATORS = List.of(
            '*', '/', '=', '<', '>', '!', '|', '&', '+', '-', '^', '%'
    );

    public static final List<Character> BRACKETS = List.of(
            '(', ')', '[', ']', '{', '}'
    );


    private LexerUtility() {
    }

    public static boolean isPlusOrMinus(char ch) {
        return ch == '+' || ch == '-';
    }

    public static boolean isLetterDigitOrUnderscore(char ch) {
        return Character.isLetterOrDigit(ch) || ch == '_';
    }

    public static boolean isOperator(char aChar) {
        return OPERATORS.contains(aChar);
    }

    public static boolean isBrackets(char cha) {
        return BRACKETS.contains(cha);
    }

    public static boolean isDot(char cha) {
        return cha == '.';
    }

    public static boolean isApostrophe(char cha) {
        return cha == '\'';
    }

    public static boolean isQuotation(char cha) {
        return cha == '\"';
    }

    public static TokenType CommaOrColonType(char cha) {
        if (isComma(cha)) {
            return TokenType.COMMA;
        } else if (isColon(cha)) {
            return TokenType.COLON;
        } else {
            return TokenType.UNKNOWN;
        }
    }

    public static boolean isCommaOrColon(char cha) {
        return isComma(cha) || isColon(cha);
    }

    public static boolean isComma(char cha) {
        return cha == ','; // Replace with actual character if different
    }

    public static boolean isColon(char cha) {
        return cha == ':'; // Replace with actual character if different
    }

    public static boolean isHasterisc(char cha) {
        return cha == '#';
    }

    public static boolean isUnderscore(char cha) {
        return cha == '_'; // Replace with actual character if different
    }

    public static boolean isOctalDigit(char cha) {
        return Character.isDigit(cha) && cha >= '0' && cha <= '7';
    }

    public static boolean isHexDigit(char ch) {
        return Character.digit(ch, 16) != -1;
    }

    public static boolean isComment(String input, int position) {
        int nextPos = position + 1;
        int inputLen = input.length();
        return position < inputLen && input.charAt(position) == '/' &&
                (nextPos < inputLen && (input.charAt(nextPos) == '/' || input.charAt(nextPos) == '*'));
    }
}

package org.dersbian.vandiorsp.model;

import java.util.EnumSet;
import java.util.Set;

public enum TokenType {
    INTEGER,
    DOUBLE,
    BOOLEAN,
    PLUS,
    MINUS,
    NOT,
    STAR,
    DIVIDE,
    XOR,
    PERCENT,
    OR,
    AND,
    EQUAL,
    LESS,
    GREATER,
    PLUSPLUS,
    MINUSMINUS,
    PLUSEQUAL,
    MINUSEQUAL,
    NOTEQUAL,
    STAREQUAL,
    DIVIDEEQUAL,
    XOREQUAL,
    PERCENTEQUAL,
    OROR,
    ANDAND,
    EQUALEQUAL,
    LESSEQUAL,
    GREATEREQUAL,
    DOT,
    IDENTIFIER,
    CHAR,
    STRING,
    K_MAIN,
    K_VAR,
    K_IF,
    K_WHILE,
    K_ELSE,
    K_FOR,
    K_BREAK,
    K_FUN,
    K_RETURN,
    K_NULLPTR,
    OPEN_PARENTESIS,
    OPEN_SQ_PARENTESIS,
    OPEN_CUR_PARENTESIS,
    CLOSE_PARENTESIS,
    CLOSE_SQ_PARENTESIS,
    CLOSE_CUR_PARENTESIS,
    COMMA,
    COLON,
    TYPE_I8,
    TYPE_I16,
    TYPE_I32,
    TYPE_I64,
    TYPE_U8,
    TYPE_U16,
    TYPE_U32,
    TYPE_U64,
    TYPE_F32,
    TYPE_F64,
    TYPE_C32,
    TYPE_C64,
    TYPE_CHAR,
    TYPE_STRING,
    TYPE_BOOL,
    COMMENT,
    UNKNOWN,
    EOFT;

    private static final Set<TokenType> KEYWORDS = EnumSet.of(K_MAIN, K_VAR, K_IF, K_WHILE, K_ELSE, K_FOR, K_BREAK, K_FUN, K_RETURN);

    public static boolean isKeyword(TokenType type) {
        return KEYWORDS.contains(type);
    }

    public static TokenType getBracketsType(String value) {
        if (value.isEmpty()) return UNKNOWN;
        char ch = value.charAt(0);
        return switch (ch) {
            case '(' -> OPEN_PARENTESIS;
            case ')' -> CLOSE_PARENTESIS;
            case '[' -> OPEN_SQ_PARENTESIS;
            case ']' -> CLOSE_SQ_PARENTESIS;
            case '{' -> OPEN_CUR_PARENTESIS;
            case '}' -> CLOSE_CUR_PARENTESIS;
            default -> UNKNOWN;
        };
    }

    public static TokenType singleCharOp(char ch) {
        return switch (ch) {
            case '-' -> MINUS;
            case '=' -> EQUAL;
            case '<' -> LESS;
            case '>' -> GREATER;
            case '!' -> NOT;
            case '+' -> PLUS;
            case '*' -> STAR;
            case '/' -> DIVIDE;
            case '^' -> XOR;
            case '%' -> PERCENT;
            case '|' -> OR;
            case '&' -> AND;
            default -> UNKNOWN;
        };
    }
}

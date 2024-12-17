package org.dersbian.vandiorsp.model;

import lombok.extern.slf4j.Slf4j;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static org.dersbian.vandiorsp.model.LexerUtility.*;

@Slf4j
public class Lexer {
    public static final Set<Map.Entry<String, TokenType>> multiCharOperators = Map.ofEntries(
            new SimpleEntry<>("+=", TokenType.PLUSEQUAL),
            new SimpleEntry<>("-=", TokenType.MINUSEQUAL),
            new SimpleEntry<>("*=", TokenType.STAREQUAL),
            new SimpleEntry<>("/=", TokenType.DIVIDEEQUAL),
            new SimpleEntry<>("^=", TokenType.XOREQUAL),
            new SimpleEntry<>("%=", TokenType.PERCENTEQUAL),
            new SimpleEntry<>("==", TokenType.EQUALEQUAL),
            new SimpleEntry<>(">=", TokenType.GREATEREQUAL),
            new SimpleEntry<>("<=", TokenType.LESSEQUAL),
            new SimpleEntry<>("!=", TokenType.NOTEQUAL),
            new SimpleEntry<>("&&", TokenType.ANDAND),
            new SimpleEntry<>("||", TokenType.OROR),
            new SimpleEntry<>("++", TokenType.PLUSPLUS),
            new SimpleEntry<>("--", TokenType.MINUSMINUS)
    ).entrySet();
    public static final Set<Map.Entry<String, TokenType>> typeArray = Map.ofEntries(
            new SimpleEntry<>("i8", TokenType.TYPE_I8),
            new SimpleEntry<>("i16", TokenType.TYPE_I16),
            new SimpleEntry<>("i32", TokenType.TYPE_I32),
            new SimpleEntry<>("i64", TokenType.TYPE_I64),
            new SimpleEntry<>("u8", TokenType.TYPE_U8),
            new SimpleEntry<>("u16", TokenType.TYPE_U16),
            new SimpleEntry<>("u32", TokenType.TYPE_U32),
            new SimpleEntry<>("u64", TokenType.TYPE_U64),
            new SimpleEntry<>("f32", TokenType.TYPE_F32),
            new SimpleEntry<>("f64", TokenType.TYPE_F64),
            new SimpleEntry<>("c32", TokenType.TYPE_C32),
            new SimpleEntry<>("c64", TokenType.TYPE_C64),
            new SimpleEntry<>("char", TokenType.TYPE_CHAR),
            new SimpleEntry<>("string", TokenType.TYPE_STRING),
            new SimpleEntry<>("bool", TokenType.TYPE_BOOL)
    ).entrySet();
    public static final Set<Map.Entry<String, TokenType>> keywordArray = Map.ofEntries(
            new SimpleEntry<>("main", TokenType.K_MAIN),
            new SimpleEntry<>("var", TokenType.K_VAR),
            new SimpleEntry<>("val", TokenType.K_VAR),
            new SimpleEntry<>("const", TokenType.K_VAR),
            new SimpleEntry<>("if", TokenType.K_IF),
            new SimpleEntry<>("while", TokenType.K_WHILE),
            new SimpleEntry<>("else", TokenType.K_ELSE),
            new SimpleEntry<>("for", TokenType.K_FOR),
            new SimpleEntry<>("break", TokenType.K_BREAK),
            new SimpleEntry<>("continue", TokenType.K_BREAK),
            new SimpleEntry<>("fun", TokenType.K_FUN),
            new SimpleEntry<>("return", TokenType.K_RETURN),
            new SimpleEntry<>("nullptr", TokenType.K_NULLPTR),
            new SimpleEntry<>("true", TokenType.BOOLEAN),
            new SimpleEntry<>("false", TokenType.BOOLEAN)
    ).entrySet();
    private final String input;
    private final FileName fileName;
    private final int inputSize;
    private int position = 0;
    private int line = 1;
    private int column = 1;

    public Lexer(String inputFilename, String input) {
        this.fileName = new FileName(inputFilename);
        this.input = input;
        this.inputSize = input.length();
    }

    public TokenType keywordType(String value) {
        // Search for keyword type in the keywordArray map
        return keywordArray.stream()
                .filter(entry -> Objects.equals(entry.getKey(), value))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseGet(() -> getType(value));  // If not found, call getType
    }

    private TokenType getType(String value) {
        // Search for type in the typeArray map
        return typeArray.stream()
                .filter(entry -> Objects.equals(entry.getKey(), value))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(TokenType.IDENTIFIER);  // Return IDENTIFIER if not found
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (positionIsInText()) {
            var caracter = input.charAt(position);
            if (Character.isAlphabetic(caracter)) {
                tokens.add(handleAlfa());
            } else if (Character.isDigit(caracter)) {
                tokens.add(handleDigits());
            } else if (isHasterisc(caracter)) {
                tokens.add(handleHexadecimalOrOctal());
            } else if (isUnderscore(caracter)) {
                tokens.add(handleUnderscoreAlpha());
            } else if (isComment(input, position)) {
                tokens.add(handleComment());
            } else if (isOperator(caracter)) {
                tokens.addAll(handleOperators());
            } else if (isDot(caracter)) {
                tokens.add(handleDot());
            } else if (isCommaOrColon(caracter)) {
                var value = caracter;
                incPosAndColumn();
                tokens.add(new Token(CommaOrColonType(value), String.valueOf(value), new CodeSourceLocation(fileName, line, column - 1)));
            } else if (isBrackets(caracter)) {
                tokens.add(handleBrackets());
            } else if (isApostrophe(caracter)) {
                tokens.add(handleChar());
            } else if (isQuotation(caracter)) {
                tokens.add(handleString());
            } else if (Character.isWhitespace(caracter)) {
                handleWhitespace();
            } else {
                throw new RuntimeException("unknown character '" + caracter + "' at column: " + column + ", line: " + line);
                //incPosAndColumn();
            }
        }
        tokens.add(new Token(TokenType.EOFT, new CodeSourceLocation(fileName, line, column)));
        return tokens;
    }

    private Token handleString() {
        final int startColumn = column;
        incPosAndColumn();
        final int start = position;
        String value = null;

        while (!isQuotation(input.charAt(position))) {
            if (position + 1 == inputSize) {
                incPosAndColumn();
                value = input.substring(start, position);
                return createToken(TokenType.UNKNOWN, value);
            }
            handleWhitespace();
        }

        value = input.substring(start, position);
        incPosAndColumn();
        return createToken(TokenType.STRING, value);
    }


    private Token handleChar() {
        incPosAndColumn();
        final int start = position;
        String value = null;

        while (!isApostrophe(input.charAt(position))) {
            if (position + 1 == inputSize) {
                incPosAndColumn();
                value = input.substring(start, position);
                return new Token(TokenType.UNKNOWN, value, new CodeSourceLocation(fileName, line, column - value.length()));
            }
            incPosAndColumn();
        }

        value = input.substring(start, position);
        final int colum = column - value.length();
        incPosAndColumn();
        return new Token(TokenType.CHAR, value, new CodeSourceLocation(fileName, line, colum));
    }

    public Token handleBrackets() {
        int start = position;
        incPosAndColumn(); // Assuming this is a method that increments position and column

        // Extracting substring from `start` to `position`
        final String value = input.substring(start, position);

        // Assuming `getBracketsType` is a method that determines the type based on `value`
        final TokenType type = TokenType.getBracketsType(value);

        // Creating and returning a new `Token` object with extracted information
        return createToken(type, value);
    }


    private Token handleDot() {
        int start = position;
        TokenType type = TokenType.DOT;
        incPosAndColumn();

        if (positionIsInText() && Character.isDigit(input.charAt(position))) {
            type = TokenType.DOUBLE;
            extractDigits();

            if (inTextAndE()) {
                incPosAndColumn();
                extractExponent();
            }

            if (inTextAnd('i')) {
                incPosAndColumn();
            }  // Assuming 'icr' is 'i'
            if (inTextAnd('f')) {
                incPosAndColumn();
            }  // Assuming 'fcr' is 'f'
        }

        String value = input.substring(start, position);
        return createToken(type, value);
    }

    private List<Token> handleOperators() {
        List<Token> tokens = new ArrayList<>();
        int start = position;
        extractVarLenOperator();
        String value = input.substring(start, position);

        while (!value.isEmpty()) {
            Token token = null;
            if (value.length() > 1) {
                String twoCharOp = value.substring(0, 2);
                token = new Token(multiCharOp(twoCharOp), twoCharOp, new CodeSourceLocation(fileName, line, column - twoCharOp.length()));
            }

            if (token == null || token.isType(TokenType.UNKNOWN) || value.length() == 1) {
                String oneCharOp = value.substring(0, 1);
                token = new Token(TokenType.singleCharOp(oneCharOp.charAt(0)), oneCharOp, new CodeSourceLocation(fileName, line, column - oneCharOp.length()));
            }

            tokens.add(token);
            value = value.substring(token.getValueSize()); // Update value by removing the processed part
        }

        return tokens;
    }

    private void extractVarLenOperator() {
        while (positionIsInText() && isOperator(input.charAt(position))) {
            incPosAndColumn();
        }
    }

    private TokenType multiCharOp(String twoCharOp) {
        return multiCharOperators.stream()
                .filter(entry -> entry.getKey().equals(twoCharOp))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(TokenType.IDENTIFIER);
    }

    public Token handleComment() {
        int nextPosition = position + 1;

        if (input.charAt(nextPosition) == '/') {
            return handleSingleLineComment();
        }

        if (input.charAt(nextPosition) == '*') {
            return handleMultiLineComment();
        }
        return createToken();
    }

    private Token handleSingleLineComment() {
        int start = position;
        while (positionIsInText() && input.charAt(position) != '\n') {
            incPosAndColumn();
        }
        String value = input.substring(start, position);
        return createToken(TokenType.COMMENT, value);
    }

    public Token handleMultiLineComment() {
        int start = position;
        int startColumn = column;

        while (!(input.charAt(position) == '*' && input.charAt(position + 1) == '/')) {
            if (!positionIsInText()) {
                String value = input.substring(start, position);
                return createToken(TokenType.UNKNOWN, value);
            }
            // Advance to the next character
            incPosAndColumn();
            handleWhitespace();
        }

        incPosAndColumn(); // Skip '*'
        incPosAndColumn(); // Skip '/'

        String value = input.substring(start, position);

        return new Token(TokenType.COMMENT, value, new CodeSourceLocation(fileName, line, startColumn));
    }


    private Token handleHexadecimalOrOctal() {
        int start = position;
        incPosAndColumn();

        if (positionIsInText() && Character.toLowerCase(input.charAt(position)) == 'o') {
            // Handle octal numbers
            incPosAndColumn();
            while (positionIsInText() && isOctalDigit(input.charAt(position))) {
                incPosAndColumn();
            }
        } else if (positionIsInText() && isHexDigit(input.charAt(position))) {
            // Handle hexadecimal numbers
            while (positionIsInText() && isHexDigit(input.charAt(position))) {
                incPosAndColumn();
            }
        } else {
            String errorValue = input.substring(start, position);
            String errorString = new StringBuilder("malformed hexadecimal number or octal number '")
                    .append(errorValue)
                    .append("' (column: ")
                    .append(column)
                    .append(", line")
                    .append(line)
                    .append("):")
                    .toString();
            throw  new IllegalArgumentException(errorString);
        }

        String value = input.substring(start, position);
        return new Token(TokenType.INTEGER, value, new CodeSourceLocation(fileName, line, column - value.length() - 1));
    }


    public Token handleUnderscoreAlpha() {
        int start = position;
        incPosAndColumn();

        while (positionIsInText() && isLetterDigitOrUnderscore(input.charAt(position))) {
            incPosAndColumn();
        }

        String value = input.substring(start, position);
        return createToken(TokenType.IDENTIFIER, value);
    }

    /*private void handleMultiWhitespace() {
        while (Character.isWhitespace(input.charAt(position))) {
            handleWhitespace();
        }
    }*/

    private void handleWhitespace() {
        if (input.charAt(position) == '\n') {
            ++line;
            column = 0;
        }
        incPosAndColumn();
    }

    private Token handleAlfa() {
        int start = position;

        while (positionIsInText() && isLetterDigitOrUnderscore(input.charAt(position))) {
            incPosAndColumn();
        }
        String value = input.substring(start, position);
        TokenType type = keywordType(value);
        int tokenColumnStart = column - value.length();
        return new Token(type, value, new CodeSourceLocation(fileName, line, tokenColumnStart));
    }

    private void incPosAndColumn() {
        ++position;
        ++column;
    }

    private boolean positionIsInText() {
        return position < inputSize;
    }

    private Token handleDigits() {
        TokenType tokenType = TokenType.INTEGER;
        int start = position;

        extractDigits();

        if (inTextAnd('.')) {
            incPosAndColumn();
            extractDigits();
            if (inTextAndE()) {
                incPosAndColumn();
                extractExponent();
            }
            tokenType = TokenType.DOUBLE;
        }

        if (inTextAndE()) {
            incPosAndColumn();
            extractExponent();
            tokenType = TokenType.DOUBLE;
        }

        if (inTextAnd('i')) {
            incPosAndColumn();
            tokenType = TokenType.DOUBLE;
        }

        if (inTextAnd('f')) {
            incPosAndColumn();
            tokenType = TokenType.DOUBLE;
        }

        String value = input.substring(start, position);
        return createToken(tokenType, value);
    }

    public void extractExponent(){
        if (positionIsInText() && isPlusOrMinus(input.charAt(position))) {
            incPosAndColumn();
        }
        extractDigits();
    }

    private void extractDigits() {
        while (positionIsInText() && Character.isDigit(input.charAt(position))) {
            incPosAndColumn();
        }
    }

    private boolean inTextAndE() {
        return positionIsInText() && (input.charAt(position) == 'e' || input.charAt(position) == 'E');
    }

    public boolean inTextAnd(char chr) {
        return positionIsInText() && input.charAt(position) == chr;
    }

    // Improved utility methods
    private Token createToken(TokenType type, String value) {
        return new Token(type, value, new CodeSourceLocation(fileName, line, column - value.length()));
    }

    private Token createToken() {
        return new Token(TokenType.UNKNOWN, new CodeSourceLocation(fileName, line, column));
    }
}
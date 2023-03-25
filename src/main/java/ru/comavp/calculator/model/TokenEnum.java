package ru.comavp.calculator.model;

import java.util.Arrays;

import static ru.comavp.calculator.model.TokenType.*;

public enum TokenEnum {

    ADD_TOKEN("+", ADD),
    SUB_TOKEN("-", SUB),
    MUL_TOKEN("*", MUL),
    DIV_TOKEN("/", DIV),
    LB_TOKEN("(", LB),
    RB_TOKEN(")", RB);

    private Token token;

    TokenEnum(String value, TokenType tokenType) {
        token = new Token(value, tokenType);
    }

    public Token getToken() {
        return token;
    }

    public String getTokenValue() {
        return token.getValue();
    }

    public static boolean isNotDigit(String value) {
        return Arrays.stream(values()).anyMatch(item -> item.getToken().getValue().equals(value));
    }

    public static Token getTokenByValue(String value) {
        return Arrays.stream(values()).filter(item -> item.getTokenValue().equals(value)).findFirst().orElseThrow().getToken();
    }
}

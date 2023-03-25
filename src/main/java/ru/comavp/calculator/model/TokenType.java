package ru.comavp.calculator.model;

public enum TokenType {

    ADD("addition"),
    SUB("subtraction"),
    MUL("multiplication"),
    DIV("division"),

    INT("integer_digit"),
    DOUBLE("double_digit"),

    LB("left_bracket"),
    RB("right_bracket");

    private String typeName;

    TokenType(String typeName) {
        this.typeName = typeName;
    }
}

package ru.comavp.calculator.model;

public enum TokenType {

    ADD("addition"),
    SUB("subtraction"),
    MUL("multiplication"),
    DIV("division"),
    UM("unary_minus"),

    INT("integer_digit"),
    DOUBLE("double_digit"),

    LP("left_parenthesis"),
    RP("right_parenthesis");

    private String typeName;

    TokenType(String typeName) {
        this.typeName = typeName;
    }
}

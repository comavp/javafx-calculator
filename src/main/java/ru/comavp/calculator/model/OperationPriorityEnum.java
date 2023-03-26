package ru.comavp.calculator.model;

import java.util.Arrays;

import static ru.comavp.calculator.model.TokenType.*;

public enum OperationPriorityEnum {

    LB_PRIORITY(LP, 0),
    ADD_PRIORITY(ADD, 1),
    SUB_PRIORITY(SUB, 1),
    MUL_PRIORITY(MUL, 2),
    DIV_PRIORITY(DIV, 2);

    private TokenType tokenType;
    private int operationPriority;

    OperationPriorityEnum(TokenType tokenType, int operationPriority) {
        this.tokenType = tokenType;
        this.operationPriority = operationPriority;
    }

    public static boolean isOperation(Token token) {
        return Arrays.stream(values()).anyMatch(item -> item.tokenType.equals(token.getType()));
    }

    public static int getOperationPriority(Token token) {
        return Arrays.stream(values()).filter(item -> item.tokenType.equals(token.getType())).findFirst().orElseThrow().operationPriority;
    }
}

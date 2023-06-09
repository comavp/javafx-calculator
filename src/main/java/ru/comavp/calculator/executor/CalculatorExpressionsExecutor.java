package ru.comavp.calculator.executor;

import ru.comavp.calculator.model.Token;
import ru.comavp.calculator.model.TokenType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Queue;
import java.util.Stack;

import static ru.comavp.calculator.model.TokenEnum.*;
import static ru.comavp.calculator.model.TokenType.DOUBLE;
import static ru.comavp.calculator.model.TokenType.UM;

public class CalculatorExpressionsExecutor implements ExpressionExecutor {

    private final double EPS = 10e-6;
    private final String DIVISION_BY_ZERO_ERROR_MESSAGE = "Деление на ноль невозможно";

    @Override
    public String calculateExpression(Queue<Token> executableExpression) {
        Stack<Token> stack = new Stack<>();

        try {
            executableExpression.forEach(token -> {
                if (isDigit(token.getValue())) {
                    stack.push(token);
                } else if (UM.equals(token.getType())) {
                    Double arg = Double.valueOf(stack.pop().getValue());
                    stack.push(executeUnaryOperation(arg, token.getType()));
                } else {
                    Double secondArg = Double.valueOf(stack.pop().getValue());
                    Double firstArg = Double.valueOf(stack.pop().getValue());
                    stack.push(executeBinaryOperation(firstArg, secondArg, token.getType()));
                }
            });
        } catch (DivisionByZeroException e) {
            return DIVISION_BY_ZERO_ERROR_MESSAGE;
        }

        return getPrettyResult(stack.pop().getValue());
    }

    private Token executeUnaryOperation(Double arg, TokenType operationType) {
        if (UM_TOKEN.getTokenType().equals(operationType)) {
            arg *= -1;
            return new Token(arg.toString(), DOUBLE);
        }

        throw new UnknownTokenException();
    }

    private Token executeBinaryOperation(Double arg1, Double arg2, TokenType operationType) {
        Double result = 0.0;
        if (ADD_TOKEN.getTokenType().equals(operationType)) {
            result = arg1 + arg2;
        } else if (SUB_TOKEN.getTokenType().equals(operationType)) {
            result = arg1 - arg2;
        } else if (MUL_TOKEN.getTokenType().equals(operationType)) {
            result = arg1 * arg2;
        } else if (DIV_TOKEN.getTokenType().equals(operationType)) {
            if (arg2 == 0.0) {
                throw new DivisionByZeroException();
            }
            result = arg1 / arg2;
        }
        return new Token(result.toString(), DOUBLE);
    }

    private String getPrettyResult(String result) {
        if (result.indexOf('.') == -1) return result;

        if (isInteger(result)) {
            return result.substring(0, result.indexOf('.'));
        } else {
            return String.valueOf(BigDecimal.valueOf(Double.parseDouble(result))
                    .setScale(6, RoundingMode.HALF_UP)
                    .doubleValue());
        }
    }

    private boolean isInteger(String digit) {
        return Math.abs(Double.parseDouble(digit) - (int) Double.parseDouble(digit)) < EPS;
    }
}

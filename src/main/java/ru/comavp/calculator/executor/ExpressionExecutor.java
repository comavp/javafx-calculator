package ru.comavp.calculator.executor;

import ru.comavp.calculator.model.Token;

import java.util.Queue;

public interface ExpressionExecutor {

    String calculateExpression(Queue<Token> executableExpression);
}

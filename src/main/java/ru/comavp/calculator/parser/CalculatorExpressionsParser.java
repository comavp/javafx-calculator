package ru.comavp.calculator.parser;

import ru.comavp.calculator.model.Token;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Predicate;

import static ru.comavp.calculator.model.OperationPriorityEnum.getOperationPriority;
import static ru.comavp.calculator.model.TokenEnum.*;

public class CalculatorExpressionsParser implements Parser {

    @Override
    public Queue<Token> getExecutableTokenList(List<Token> tokenList) {
        Queue<Token> result = new ArrayDeque<>();
        Stack<Token> stack = new Stack<>();

        // (5+3)*3/12-3.7
        tokenList.forEach(token -> {
            if (isDigit(token.getValue())) {
                result.add(token);
            } else if (LP_TOKEN.getTokenType().equals(token.getType())) {
                stack.push(token);
            } else if (RP_TOKEN.getTokenType().equals(token.getType())) {
                result.addAll(popAllFromStack(
                        stack,
                        item -> !LP_TOKEN.getTokenType().equals(item.getType())
                ));
                stack.pop();
            } else {
                result.addAll(popAllFromStack(
                        stack,
                        item -> getOperationPriority(item) >= getOperationPriority(token)
                ));
                stack.push(token);
            }
        });

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private Queue<Token> popAllFromStack(Stack<Token> stack, Predicate<Token> condition) {
        Queue<Token> result = new ArrayDeque<>();
        while (!stack.isEmpty() && condition.test(stack.peek())) {
            result.add(stack.pop());
        }

        return result;
    }
}

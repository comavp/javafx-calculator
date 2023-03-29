package ru.comavp.calculator;

import ru.comavp.calculator.executor.CalculatorExpressionsExecutor;
import ru.comavp.calculator.lexer.CalculatorExpressionsAnalyzer;
import ru.comavp.calculator.parser.CalculatorExpressionsParser;

public class PostfixCalculator implements Calculator {

    @Override
    public String getExpressionResult(String expression) {
        return new CalculatorExpressionsExecutor().calculateExpression(
                new CalculatorExpressionsParser().getExecutableTokenList(
                        new CalculatorExpressionsAnalyzer().getTokenListFromExpression(expression)
                )
        );
    }
}

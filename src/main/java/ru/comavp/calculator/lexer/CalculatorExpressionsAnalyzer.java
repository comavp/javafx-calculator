package ru.comavp.calculator.lexer;

import ru.comavp.calculator.model.Token;
import ru.comavp.calculator.model.TokenEnum;
import ru.comavp.calculator.model.TokenType;

import java.util.ArrayList;
import java.util.List;

import static ru.comavp.calculator.model.TokenType.DOUBLE;
import static ru.comavp.calculator.model.TokenType.INT;

public class CalculatorExpressionsAnalyzer implements Lexer {

    private List<Token> tokenList;
    private Integer currentInd;

    public List<Token> getTokenListFromExpression(String expression) {
        tokenList = new ArrayList<>();
        currentInd = 0;

        while (currentInd < expression.length()) {
            Character currentSymbol = expression.charAt(currentInd);
            if (TokenEnum.isNotDigit(currentSymbol.toString())) {
                tokenList.add(TokenEnum.getTokenByValue(currentSymbol.toString()));
            } else {
                tokenList.add(getDigit(currentSymbol, expression));
            }

            currentInd++;
        }

        return tokenList;
    }

    private Token getDigit(Character currentSymbol, String expression) {
        int size = expression.length();
        TokenType type = INT;
        String result = "";

        while (currentInd < size && (Character.isDigit(currentSymbol) || currentSymbol == '.')) {
            if (currentSymbol == '.') {
                type = DOUBLE;
            }
            result += currentSymbol;
            currentInd++;
            if (currentInd < size) {
                currentSymbol = expression.charAt(currentInd);
            }
        }

        currentInd--;

        return new Token(result, type);
    }
}

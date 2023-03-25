package ru.comavp.calculator.lexer;

import ru.comavp.calculator.model.Token;

import java.util.List;

public interface Lexer {

    List<Token> getTokenListFromExpression(String expression);
}

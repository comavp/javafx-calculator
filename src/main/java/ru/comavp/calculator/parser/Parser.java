package ru.comavp.calculator.parser;

import ru.comavp.calculator.model.Token;

import java.util.List;
import java.util.Queue;

public interface Parser {

    Queue<Token> getExecutableTokenList(List<Token> tokenList);
}

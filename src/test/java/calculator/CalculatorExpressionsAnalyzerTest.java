package calculator;

import org.junit.Test;
import ru.comavp.calculator.lexer.CalculatorExpressionsAnalyzer;
import ru.comavp.calculator.lexer.Lexer;
import ru.comavp.calculator.model.Token;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.comavp.calculator.model.TokenType.*;

public class CalculatorExpressionsAnalyzerTest {

    Lexer lexer = new CalculatorExpressionsAnalyzer();

    @Test
    public void getTokenListFromValidExpressionTest() {
        String expression = "(-5+3)*3/12-3.7";
        List<Token> expectedTokenList = Arrays.asList(
                new Token("(", LP),
                new Token("-", UM),
                new Token("5", INT),
                new Token("+", ADD),
                new Token("3", INT),
                new Token(")", RP),
                new Token("*", MUL),
                new Token("3", INT),
                new Token("/", DIV),
                new Token("12", INT),
                new Token("-", SUB),
                new Token("3.7", DOUBLE)
        );

        List<Token> actualTokenList = lexer.getTokenListFromExpression(expression);

        assertNotNull(actualTokenList);
        assertEquals(expectedTokenList.size(), actualTokenList.size());
        checkIfTokenListEquals(expectedTokenList, actualTokenList);
    }

    @Test
    public void getTokenListFromEmptyExpressionTest() {
        List<Token> actualTokenList = lexer.getTokenListFromExpression("");

        assertNotNull(actualTokenList);
        assertEquals(0, actualTokenList.size());
    }

    private void checkIfTokenListEquals(List<Token> expected, List<Token> actual) {
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                assertEquals(expected.get(i), actual.get(i));
            }
        }
    }
}

package calculator;

import org.junit.Test;
import ru.comavp.calculator.model.Token;
import ru.comavp.calculator.parser.CalculatorExpressionsParser;
import ru.comavp.calculator.parser.Parser;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.comavp.calculator.model.TokenType.*;

public class CalculatorExpressionsParserTest {

    Parser parser = new CalculatorExpressionsParser();

    @Test
    public void getExecutableTokenListTest() {
        List<Token> tokenList = Arrays.asList(
                new Token("(", LP),
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
        Queue<Token> expectedExecutableTokenList = new ArrayDeque<>() {{
            add(new Token("5", INT));
            add(new Token("3", INT));
            add(new Token("+", ADD));
            add(new Token("3", INT));
            add(new Token("*", MUL));
            add(new Token("12", INT));
            add(new Token("/", DIV));
            add(new Token("3.7", DOUBLE));
            add(new Token("-", SUB));
        }};

        Queue<Token> actualExecutableTokenList = parser.getExecutableTokenList(tokenList);

        assertNotNull(actualExecutableTokenList);
        assertEquals(expectedExecutableTokenList.size(), actualExecutableTokenList.size());
        checkIfTokenListEquals(expectedExecutableTokenList, actualExecutableTokenList);
    }

    private void checkIfTokenListEquals(Queue<Token> expected, Queue<Token> actual) {
        while (!expected.isEmpty() && !actual.isEmpty()) {
            Token expectedItem = expected.poll();
            Token actualItem = actual.poll();
            assertEquals(expectedItem, actualItem);
        }
    }
}

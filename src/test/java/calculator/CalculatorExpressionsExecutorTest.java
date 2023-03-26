package calculator;

import org.junit.Test;
import ru.comavp.calculator.executor.CalculatorExpressionsExecutor;
import ru.comavp.calculator.executor.ExpressionExecutor;
import ru.comavp.calculator.model.Token;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.comavp.calculator.model.TokenType.*;

public class CalculatorExpressionsExecutorTest {

    ExpressionExecutor expressionExecutor = new CalculatorExpressionsExecutor();

    @Test
    public void calculateExpressionTest() {
        Queue<Token> executableExpression = new ArrayDeque<>() {{
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
        String expectedResult = "-1.7";

        String actualResult = expressionExecutor.calculateExpression(executableExpression);

        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }
}

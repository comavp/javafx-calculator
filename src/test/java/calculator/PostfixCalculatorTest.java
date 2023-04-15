package calculator;

import org.junit.Test;
import ru.comavp.calculator.Calculator;
import ru.comavp.calculator.PostfixCalculator;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PostfixCalculatorTest {

    @Test
    public void calculateRegularArithmeticExpressionsTest() {
        Calculator calculator = new PostfixCalculator();

        Map<String, String> expectedResultsMap = new HashMap<>() {{
            put("2+5", "7");
            put("2-6", "-4");
            put("4*2","8");
            put("4/2", "2");
            put("(4+5)*9", "81");
            put("(5+5)/3", "3.333333");
            put("4.2/2", "2.1");
            put("4.0/2.0", "2");
            put("4*(5*(3+3))/(3+0)", "40");
            put("4/0", "Деление на ноль невозможно");
            put("-5+10", "5");
            put("-23.0+43", "20");
            put("-(-9)", "9");
            put("-(-(-9))", "-9");
        }};

        expectedResultsMap.forEach((expression, expectedResult) -> {
            assertEquals(expectedResult, calculator.getExpressionResult(expression));
        });
    }
}

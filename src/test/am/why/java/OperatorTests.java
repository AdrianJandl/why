package am.why.java;

import am.why.java.interpreter.Operator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by michi on 08/05/2017.
 */
public class OperatorTests {

    @Test
    public void testS() {
        Object[] input = {1, 3, 6, "foo", 4, "bar"};
        Object[] expected = {1, 9, 36, "foo", 16, "bar"};
        Operator operator = Operator.S;

        for (int i = 0; i < input.length; i++) {
            assertEquals(operator.getUnaryOperator().apply(input[i]), expected[i]);
        }
    }
    @Test
    public void testI() {
        Object[] input = {1, 3, 6, "foo", 4, "bar"};
        Object[] expected = {2, 4, 7, "foo", 5, "bar"};
        Operator operator = Operator.I;

        for (int i = 0; i < input.length; i++) {
            assertEquals(operator.getUnaryOperator().apply(input[i]), expected[i]);
        }
    }
    @Test
    public void testD() {
        Object[] input = {1, 3, 6, "foo", 4, "bar"};
        Object[] expected = {0, 2, 5, "foo", 3, "bar"};
        Operator operator = Operator.D;

        for (int i = 0; i < input.length; i++) {
            assertEquals(operator.getUnaryOperator().apply(input[i]), expected[i]);
        }
    }

}

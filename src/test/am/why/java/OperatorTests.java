package am.why.java;

import am.why.java.interpreter.Operator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by michi on 08/05/2017.
 */
public class OperatorTests {

    @Test
    public void test_S() {
        Object[] input = {1, 3, 6, "foo", 4, "bar"};
        Object[] expected = {1, 9, 36, "foo", 16, "bar"};
        Operator operator = Operator.S;

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], operator.getUnaryOperator().apply(input[i]));
        }
    }

    @Test
    public void test_I() {
        Object[] input = {1, 3, 6, "foo", 4, "bar"};
        Object[] expected = {2, 4, 7, "foo", 5, "bar"};
        Operator operator = Operator.I;

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], operator.getUnaryOperator().apply(input[i]));
        }
    }

    @Test
    public void test_D() {
        Object[] input = {1, 3, 6, "foo", 4, "bar"};
        Object[] expected = {0, 2, 5, "foo", 3, "bar"};
        Operator operator = Operator.D;

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], operator.getUnaryOperator().apply(input[i]));
        }
    }

    @Test
    public void test_b() {
        Object[] input = {1, 3, 6, "f", 4, "b"};
        Object[] expected = {"1", "11", "110", "01100110", "100", "01100010"};
        Operator operator = Operator.b;

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], operator.getUnaryOperator().apply(input[i]));
        }
    }

    @Test
    public void test_r() {
        Object[] input = {10, 34, 61, "foo", 4, "bar"};
        Object[] expected = {"01", "43", "16", "oof", "4", "rab"};
        Operator operator = Operator.r;

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], operator.getUnaryOperator().apply(input[i]));
        }
    }

    @Test
    public void test_P() {
        Object[] input = {10, 33, 66, "foof", 46, "bar"};
        Object[] expected = {"false", "true", "true", "true", "false", "false"};
        Operator operator = Operator.P;

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], operator.getUnaryOperator().apply(input[i]));
        }
    }

    @Test
    public void test_U() {
        Object[] input = {10, 34, 61, "foo", 4, "BaR"};
        Object[] expected = {10, 34, 61, "FOO", 4, "BAR"};
        Operator operator = Operator.U;

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], operator.getUnaryOperator().apply(input[i]));
        }
    }

    @Test
    public void test_l() {
        Object[] input = {10, 34, 61, "FOO", 4, "bAr"};
        Object[] expected = {10, 34, 61, "foo", 4, "bar"};
        Operator operator = Operator.l;

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], operator.getUnaryOperator().apply(input[i]));
        }
    }


}

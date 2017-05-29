package am.why.java;

import am.why.java.interpreter.Selector;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Adrian on 08.05.2017.
 */
public class SelectorTests {
    @Test
    public void test_n() {
        String p = "-3";
        String n = "4";
        Selector selector = Selector.n;
        assertTrue(selector.getPredicate().test(p));
        assertFalse(selector.getPredicate().test(n));
    }

    @Test
    public void test_p() {
        String p = "4";
        String n = "-3";
        Selector selector = Selector.p;
        assertTrue(selector.getPredicate().test(p));
        assertFalse(selector.getPredicate().test(n));
    }

    @Test
    public void test_o() {
        String p = "3";
        String n = "20";
        Selector selector = Selector.o;
        assertTrue(selector.getPredicate().test(p));
        assertFalse(selector.getPredicate().test(n));
    }

    @Test
    public void test_e() {
        String p = "0";
        String n = "5";
        Selector selector = Selector.e;
        assertTrue(selector.getPredicate().test(p));
        assertFalse(selector.getPredicate().test(n));
    }

    @Test
    public void test_0() {
        String p = "-3";
        String n = "4";
        Selector selector = Selector._0;
        assertTrue(selector.getPredicate().test(p));
        assertTrue(selector.getPredicate().test(n));
    }

    @Test
    public void test_E() {
        String p = "24";
        String n = "7";
        Selector selector = Selector.E;
        assertTrue(selector.getPredicate().test(p));
        assertFalse(selector.getPredicate().test(n));
    }

    @Test
    public void test_O() {
        String p = "-3";
        String n = "4";
        Selector selector = Selector.O;
        assertTrue(selector.getPredicate().test(p));
        assertFalse(selector.getPredicate().test(n));
    }
}

package am.why.java;


import org.junit.Test;

import am.why.java.exception.YException;
import am.why.java.interpreter.Command;
import am.why.java.interpreter.Operator;
import am.why.java.interpreter.Selector;
import am.why.java.scanner.YScanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class YScannerTest {
    @Test
    public void testGetNextCommand1() throws Exception {
    }

    @Test
    public void testHasNext() throws Exception {
    }

    @Test(expected = YException.class)
    public void testConstructor() {
        YScanner error = new YScanner("wasde");
    }

    @Test
    public void testGetNextCommand() {
        YScanner x = new YScanner("oSeI");
        Command first = x.getNextCommand();
        assertEquals(Selector.o, first.getSelector());
        assertEquals(Operator.S, first.getOperator());
        Command second = x.getNextCommand();
        assertEquals(Selector.e, second.getSelector());
        assertEquals(Operator.I, second.getOperator());
    }

    @Test
    public void testDebug() {
        String input = "_eI";
        YScanner yScanner = new YScanner(input);
        assertEquals(new Command(null, null), yScanner.getNextCommand());
        assertEquals(new Command(Selector.e, Operator.I), yScanner.getNextCommand());
    }

    @Test(expected = YException.class)
    public void strictFollowedByForbiddenSelector() {
        String input = "seI";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    public void strictFollowedByAllowed() {
        String input = "s3I";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    public void lengthShorterThanFollowedByAllowed() {
        String input = "l3S";
        YScanner yScanner = new YScanner(input);
    }

    @Test(expected = YException.class)
    public void lengthShorterThanFollowedByForbidden() {
        String input = "lnS";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    public void lengthLongerThanFollowedByAllowed() {
        String input = "L3S";
        YScanner yScanner = new YScanner(input);
    }

    @Test(expected = YException.class)
    public void lengthLongerThanFollowedByForbidden() {
        String input = "LNS";
        YScanner yScanner = new YScanner(input);
    }

}

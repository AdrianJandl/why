package test;

import exception.YException;
import interpreter.Command;
import org.testng.annotations.Test;
import scanner.YScanner;

import static org.testng.Assert.assertTrue;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class YScannerTest {

    @Test(expectedExceptions = YException.class)
    public void testConstructor() {
        YScanner error = new YScanner("wasde");
    }

    @Test
    public void testGetNextCommand() {
        YScanner x = new YScanner("oSeI");
        Command first = x.getNextCommand();
        assertTrue(first.getControl().equals('o'));
        assertTrue(first.getOperator().equals('S'));
        Command second = x.getNextCommand();
        assertTrue(second.getControl().equals('e'));
        assertTrue(second.getOperator().equals('I'));
    }

    @Test(expectedExceptions = YException.class)
    public void strictFollowedByForbiddenSelector() {
        String input = "_seI";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    public void strictFollowedByAllowed() {
        String input = "_s3I";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    public void lengthShorterThanFollowedByAllowed() {
        String input = "l3S";
        YScanner yScanner = new YScanner(input);
    }

    @Test(expectedExceptions = YException.class)
    public void lengthShorterThanFollowedByForbidden() {
        String input = "lnS";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    public void lengthLongerThanFollowedByAllowed() {
        String input = "L3S";
        YScanner yScanner = new YScanner(input);
    }

    @Test(expectedExceptions = YException.class)
    public void lengthLongerThanFollowedByForbidden() {
        String input = "LNS";
        YScanner yScanner = new YScanner(input);
    }

}

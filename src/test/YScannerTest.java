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



}

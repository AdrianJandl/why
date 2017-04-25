package test;

import exception.YException;
import interpreter.Command;
import interpreter.Operator;
import interpreter.Selector;
import org.junit.jupiter.api.Test;
import scanner.YScanner;

import java.nio.channels.SeekableByteChannel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Adrian on 12-Apr-17.
 */
class YScannerTest {
    @Test
    void testGetNextCommand1() throws Exception {
    }

    @Test
    void testHasNext() throws Exception {
    }

    @Test
    void testConstructor() {
        assertThrows(YException.class, () -> {
            YScanner error = new YScanner("wasde");
        });
    }

    @Test
    void testGetNextCommand() {
        YScanner x = new YScanner("oSeI");
        Command first = x.getNextCommand();
        assertEquals(Selector.o, first.getSelector());
        assertEquals(Operator.S, first.getOperator());
        Command second = x.getNextCommand();
        assertEquals(Selector.e, second.getSelector());
        assertEquals(Operator.I, second.getOperator());
    }

    @Test
    void strictFollowedByForbiddenSelector() {
        String input = "_seI";
        assertThrows(YException.class, () -> {
            YScanner yScanner = new YScanner(input);
        });
    }

    @Test
    void strictFollowedByAllowed() {
        String input = "_s3I";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    void lengthShorterThanFollowedByAllowed() {
        String input = "l3S";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    void lengthShorterThanFollowedByForbidden() {
        String input = "lnS";
        assertThrows(YException.class, () -> {
            YScanner yScanner = new YScanner(input);
        });
    }

    @Test
    void lengthLongerThanFollowedByAllowed() {
        String input = "L3S";
        YScanner yScanner = new YScanner(input);
    }

    @Test
    void lengthLongerThanFollowedByForbidden() {
        String input = "LNS";
        assertThrows(YException.class, () -> {
            YScanner yScanner = new YScanner(input);
        });
    }

}

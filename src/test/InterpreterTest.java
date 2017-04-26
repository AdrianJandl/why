package test;

import interpreter.Command;
import interpreter.Interpreter;
import interpreter.Operator;
import interpreter.Selector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import scanner.YScanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Adrian on 26.04.2017.
 */
class InterpreterTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static PrintStream out;

    @BeforeAll
    static void setUp() {
        out = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void tearDown() {
        System.setOut(out);
    }

    @Test
    void testDebug() {
        YScanner yScanner = mock(YScanner.class);
        when(yScanner.getNextCommand()).thenReturn(new Command(null, null), new Command(Selector.e, Operator.I), new Command(Selector.e, Operator.I));
        when(yScanner.hasNext()).thenReturn(true, true, true, false);
        Interpreter interpreter = new Interpreter(yScanner, "1,2,3,4");
        assertEquals("storage.IntegerStorage{storage=[2, 2, 4, 4]}\r\n" +
                "storage.IntegerStorage{storage=[3, 2, 5, 4]}\r\n" +
                "storage.IntegerStorage{storage=[3, 2, 5, 4]}\r\n".trim(), outContent.toString().trim());
    }
}
package test;

import interpreter.Command;
import interpreter.Interpreter;
import interpreter.Operator;
import interpreter.Selector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import scanner.YScanner;
import storage.StringStorage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertEquals("storage.StringStorage{storage=[2, 2, 4, 4]}\r\n" +
                "storage.StringStorage{storage=[3, 2, 5, 4]}\r\n" +
                "storage.StringStorage{storage=[3, 2, 5, 4]}\r\n".trim(), outContent.toString().trim());
    }

    @Test
    void testPalindrome() {
        YScanner yScanner = mock(YScanner.class);
        when(yScanner.getNextCommand()).thenReturn(new Command(Selector._0, Operator.P));
        when(yScanner.hasNext()).thenReturn(true, false);
        Interpreter interpreter = new Interpreter(yScanner, "hello,world,this,cattac,doggod,testing,testset");
        assertTrue(interpreter.getStorage() instanceof StringStorage);
        String results[] = {"false", "false", "false", "true", "true", "false", "true"};
        for (int i = 0; i < interpreter.getStorage().getStorage().length; i++) {
            assertEquals(results[i], interpreter.getStorage().getStorage()[i]);
        }
    }

    @Test
    void testBitConvertInts(){
        YScanner yScanner = mock(YScanner.class);
        when(yScanner.getNextCommand()).thenReturn(new Command(Selector._0, Operator.b));
        when(yScanner.hasNext()).thenReturn(true, false);
        Interpreter interpreter = new Interpreter(yScanner, "1,4,15,128,241,30");
        assertTrue(interpreter.getStorage() instanceof StringStorage);
        String results[] = {"1", "100", "1111", "10000000", "11110001", "11110"};
        for (int i = 0; i < interpreter.getStorage().getStorage().length; i++) {
            assertEquals(results[i], interpreter.getStorage().getStorage()[i]);
        }
    }
}
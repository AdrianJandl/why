package am.why.java;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import am.why.java.interpreter.Command;
import am.why.java.interpreter.Interpreter;
import am.why.java.interpreter.Operator;
import am.why.java.interpreter.Selector;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import am.why.java.scanner.YScanner;
import am.why.java.storage.StringStorage;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Adrian on 26.04.2017.
 */
public class InterpreterTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static PrintStream out;

    @Before
    public void setUp() {
        out = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(out);
    }

    @Test
    public void testDebug() {
        YScanner yScanner = mock(YScanner.class);
        when(yScanner.getNextCommand()).thenReturn(new Command(null, null), new Command(Selector.e, Operator.I), new Command(Selector.e, Operator.I));
        when(yScanner.hasNext()).thenReturn(true, true, true, false);
        Interpreter interpreter = new Interpreter(yScanner, "1,2,3,4");
        String s = "main.StringStorage{main.why.storage=[2, 2, 4, 4]}main.StringStorage{main.why.storage=[3, 2, 5, 4]}main.StringStorage{main.why.storage=[3, 2, 5, 4]}";
        assertEquals(s.replaceAll("\\s", ""), outContent.toString().replaceAll("\\s", ""));
    }

    @Test
    public void testPalindrome() {
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
    public void testBitConvertInts() {
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
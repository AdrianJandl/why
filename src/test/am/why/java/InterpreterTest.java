package am.why.java;


import am.why.java.interpreter.*;
import am.why.java.scanner.YScanner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Adrian on 26.04.2017.
 */
public class InterpreterTest {

    @Test
    public void testDebug() {
        YScanner yScanner = mock(YScanner.class);
        when(yScanner.getNextCommand()).thenReturn(new Command(null, Selector.e, Operator.I), new Command(null, Selector.e, Operator.I));
        when(yScanner.hasNext()).thenReturn(true, true, false);
        Interpreter interpreter = new Interpreter(yScanner, "1,2,3,4", true);
        interpreter.interpret();
        List<String> list = new ArrayList<>();
        list.add("[2, 2, 4, 4]");
        list.add("[3, 2, 5, 4]");
        assertEquals(list, interpreter.getHistory());
    }

    @Test
    public void testPalindrome() {
        YScanner yScanner = mock(YScanner.class);
        when(yScanner.getNextCommand()).thenReturn(new Command(null, Selector._0, Operator.P));
        when(yScanner.hasNext()).thenReturn(true, false);
        Interpreter interpreter = new Interpreter(yScanner, "hello,world,this,cattac,doggod,testing,testset", false);
        interpreter.interpret();
        String results[] = {"false", "false", "false", "true", "true", "false", "true"};
        for (int i = 0; i < interpreter.getStorage().getStorage().length; i++) {
            assertEquals(results[i], interpreter.getStorage().getStorage()[i]);
        }
    }

    @Test
    public void testBitConvertInts() {
        YScanner yScanner = mock(YScanner.class);
        when(yScanner.getNextCommand()).thenReturn(new Command(null, Selector._0, Operator.b));
        when(yScanner.hasNext()).thenReturn(true, false);
        Interpreter interpreter = new Interpreter(yScanner, "1,4,15,128,241,30", false);
        interpreter.interpret();
        String results[] = {"1", "100", "1111", "10000000", "11110001", "11110"};
        for (int i = 0; i < interpreter.getStorage().getStorage().length; i++) {
            assertEquals(results[i], interpreter.getStorage().getStorage()[i]);
        }
    }
}
package am.why.java;


import am.why.java.interpreter.*;
import am.why.java.scanner.YScanner;
import org.junit.Test;

import java.math.BigDecimal;
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
        List<Command> commands = new ArrayList<>();
        commands.add(new Command(null, Selector.e, Operator.I));
        commands.add(new Command(null, Selector.e, Operator.I));
        when(yScanner.hasNext()).thenReturn(true, false);
        when(yScanner.getNextStep()).thenReturn(new Step(commands));
        Interpreter interpreter = new Interpreter(yScanner, "1,2,3,4", true);
        interpreter.interpret();
        List<String> list = new ArrayList<>();
        list.add("[2, 2, 4, 4]");
        list.add("[3, 2, 5, 4]");
    }

    @Test
    public void testPalindrome() {
        YScanner yScanner = mock(YScanner.class);

        Selector selector = Selector.number;
        Command command = new Command(null, selector, Operator.P);
        Step step = new Step();
        step.addCommand(command, null, new BigDecimal(0));
        when(yScanner.getNextStep()).thenReturn(step);
        when(yScanner.hasNext()).thenReturn(true, false);
        Interpreter interpreter = new Interpreter(yScanner, "hello,world,this,cattac,doggod,testing,testset", false);
        interpreter.interpret();
        String results[] = {"false", "false", "false", "true", "true", "false", "true"};
        for (int i = 0; i < interpreter.getStorage().getArray().length; i++) {
            assertEquals(results[i], interpreter.getStorage().getArray()[i]);
        }
    }

    @Test
    public void testBitConvertInts() {
        YScanner yScanner = mock(YScanner.class);

        Selector selector = Selector.number;
        Command command = new Command(null, selector, Operator.b);
        Step step = new Step();
        step.addCommand(command, null, new BigDecimal(0));
        when(yScanner.getNextStep()).thenReturn(step);
        when(yScanner.hasNext()).thenReturn(true, false);
        Interpreter interpreter = new Interpreter(yScanner, "1,4,15,128,241,30", false);
        interpreter.interpret();
        String results[] = {"1", "100", "1111", "10000000", "11110001", "11110"};
        for (int i = 0; i < interpreter.getStorage().getArray().length; i++) {
            assertEquals(results[i], interpreter.getStorage().getArray()[i]);
        }
    }
}
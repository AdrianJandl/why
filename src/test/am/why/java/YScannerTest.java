package am.why.java;


import am.why.java.interpreter.Step;

import am.why.java.exception.YException;
import am.why.java.interpreter.Command;
import am.why.java.interpreter.Operator;
import am.why.java.interpreter.Selector;
import am.why.java.scanner.YScanner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class YScannerTest {

    @Test
    public void testGetNextCommand() {
        YScanner x = new YScanner("oSeI");
        x.newParse();
        x.newParse();
        Step firstStep = x.getNextStep();
        assertEquals(2, firstStep.getCommands().size());
        Command first = firstStep.getCommands().get(0);
        assertEquals(Selector.o, first.getSelector());
        assertEquals(Operator.S, first.getOperator());
        Command second = firstStep.getCommands().get(1);
        assertEquals(Selector.e, second.getSelector());
        assertEquals(Operator.I, second.getOperator());
    }

    @Test(expected = YException.class)
    public void strictFollowedByForbiddenSelector() {
        String input = "seI";
        YScanner yScanner = new YScanner(input);
        yScanner.newParse();
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
        yScanner.newParse();
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
        yScanner.newParse();
    }

    @Test
    public void testStepsClosedBrackets() {
        String input = "(OI(OS))";
        YScanner yScanner = new YScanner(input);
        yScanner.newParse();
        Step first = yScanner.getNextStep();
        Step firstExpected = new Step();
        firstExpected.addCommand(new Command(null, Selector.O, Operator.S));
        assertEquals(firstExpected, first);
        Step second = yScanner.getNextStep();
        Step secondExpected = new Step();
        secondExpected.addCommand(new Command(null, Selector.O, Operator.I));
        assertEquals(secondExpected, second);
    }

    @Test
    public void testStepsOpenedBrackets() {
        String input = "(OI(OS";
        YScanner yScanner = new YScanner(input);
        yScanner.newParse();
        Step first = yScanner.getNextStep();
        Step firstExpected = new Step();
        firstExpected.addCommand(new Command(null, Selector.O, Operator.S));
        assertEquals(firstExpected, first);
        Step second = yScanner.getNextStep();
        Step secondExpected = new Step();
        secondExpected.addCommand(new Command(null, Selector.O, Operator.I));
        assertEquals(secondExpected, second);
    }
}

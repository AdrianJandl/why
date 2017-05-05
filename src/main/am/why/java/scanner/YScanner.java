package am.why.java.scanner;


import am.why.java.exception.YException;
import am.why.java.interpreter.*;

import java.util.Arrays;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class YScanner {
    private int programCounter;
    private String program;

    private YScanner() {
        programCounter = 0;
    }

    public YScanner(String program) {
        this();
        this.program = program;
    }

    public boolean checkSyntax() {
        char a[] = program.toCharArray();
        for (int i = 0; i < a.length; i++) {
            ControlSelector controlSelector = null;
            try {
                controlSelector = ControlSelector.from(a[i]);
                i++;
            } catch (Exception e) {
                //swallow this - this only means that current is no ControlSelector
            }
            try {
                Selector selector = Selector.from(a[i]);
                i++;
                if (controlSelector != null) {
                    if (!Arrays.asList(controlSelector.getFollowedBy()).contains(selector)) {
                        throw new YException("Syntax error. Control Selector followed by unallowed Selector at index [" + i + "] in \"" + program + "\"");
                    }
                }
                Operator operators = Operator.from(a[i]);
            } catch (IllegalArgumentException o_O) {
                throw new YException("Syntax error at index [" + i + "] in \"" + program + "\"");
            }
        }
        return true;
    }

    public Command getNextCommand() {
        Command command = new Command(Selector.from(program.charAt(programCounter)), Operator.from(program.charAt(programCounter + 1)));
        programCounter += 2;
        return command;
    }

    public boolean hasNext() {
        return programCounter + 2 < program.length();
    }
}

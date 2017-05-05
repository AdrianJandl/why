package main.java.scanner;

import main.java.exception.YException;
import main.java.interpreter.*;

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
        checkSyntax(program);
        this.program = program;
    }

    private void checkSyntax(String program) {
        char a[] = program.toCharArray();
        for (int i = 0; i < a.length; i++) {
            try {
                Special special = Special.from(a[i]);
                continue;
            } catch (Exception e) {
                //swallow this - this only means that current is no Special
            }
            //current is no Special
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
    }

    public Command getNextCommand() {
        if (programCounter == 0) {
            try {
                Special special = Special.from(program.charAt(programCounter));
                programCounter++;
                return new Command(null, null); //TODO this definitely needs a fix lmao
            } catch (IllegalArgumentException iae) {
                //swallow this - this only means that the first character is no Special
            }
        }
        Command command = new Command(Selector.from(program.charAt(programCounter)), Operator.from(program.charAt(programCounter + 1)));
        programCounter += 2;
        return command;
    }

    public boolean hasNext() {
        return programCounter + 2 < program.length();
    }
}

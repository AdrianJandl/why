package am.why.java.scanner;


import am.why.java.exception.YException;
import am.why.java.interpreter.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class YScanner {
    private String program;
    private boolean syntaxChecked = false;
    private List<Statement> commandList;
    private Iterator<Statement> commandIterator;
    private int bracketCounter;

    public YScanner(String program) {
        this.program = program;
        commandList = new ArrayList<>();
        bracketCounter = 0;
    }

    public boolean checkSyntax() {
        if (syntaxChecked) {
            throw new YException("Illegal duplicate call to checkSyntax!");
        }
        char a[] = program.toCharArray();
        for (int i = 0; i < a.length; i++) {
            Special special = null;
            try {
                special = Special.from(a[i]);
                if (special == Special.OPEN_BRACKET) {
                    bracketCounter++;
                    commandList.add(new Control(Special.OPEN_BRACKET));
                } else if (special == Special.CLOSED_BRACKET) {
                    bracketCounter--;
                    commandList.add(new Control(Special.CLOSED_BRACKET));
                }
                if (bracketCounter < 0) {
                    throw new YException("Syntax error at index [" + i + "]. Unexpected character ')'");
                }
                i++;
            } catch (Exception e) {
                //swallow this - this only means that current is no Special
            }
            if (i>=a.length){
                break;
            }
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
                commandList.add(new Command(controlSelector, selector, operators));
            } catch (IllegalArgumentException o_O) {
                throw new YException("Syntax error at index [" + i + "] in \"" + program + "\"");
            }
        }
        commandIterator = commandList.iterator();
        syntaxChecked = true;
        if (bracketCounter != 0) { //TODO should we auto-close brackets to save bytes in programs?
            throw new YException("Syntax error. Unclosed parantheses.");
        }
        return true;
    }

    public Statement getNextCommand() {
        if (commandIterator.hasNext()) {
            return commandIterator.next();
        }
        return null;
    }

    public boolean hasNext() {
        return commandIterator.hasNext();
    }
}

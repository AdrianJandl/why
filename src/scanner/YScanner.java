package scanner;

import exception.YException;
import interpreter.*;

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
                    Selector[] followedBy = controlSelector.getFollowedBy();
                    boolean found = false;
                    for (Selector possibility : followedBy) {
                        if (possibility == selector) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        throw new YException("Syntax error. Control Selector followed by unallowed Selector at index [" + i + "] in \"" + program + "\"");
                    }
                }
                Operators operators = Operators.from(a[i]);
            } catch (IllegalArgumentException o_O) {
                throw new YException("Syntax error at index [" + i + "] in \"" + program + "\"");
            }
        }
    }

    public Command getNextCommand() {
        Command command = new Command(program.charAt(programCounter), program.charAt(programCounter + 1));
        programCounter += 2;
        return command;
    }

    public boolean hasNext() {
        return programCounter + 2 < program.length();
    }
}

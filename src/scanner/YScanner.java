package scanner;

import exception.YException;
import interpreter.Command;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class YScanner {
    private int programCounter;
    private String program;

    private YScanner() {
        programCounter = 0;
    }

    public YScanner(String program) throws YException {
        this();
        if (program.length() % 2 != 0) {
            throw new YException("Syntax error. Program length incorrect.");
        }
        this.program = program;
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

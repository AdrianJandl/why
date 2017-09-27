package am.why.java;

import am.why.java.interpreter.Interpreter;
import am.why.java.scanner.YScanner;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Main {
    /**
     * @param args args[0] program code, args[1] input
     */
    public static void main(String[] args) {
        // sets debug flag on interpreter
        boolean isDebug = false;
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: args[0] program code, args[1] input.");
        }
        YScanner yScanner = new YScanner(args[0]);
        yScanner.parse();
        Interpreter interpreter = new Interpreter(yScanner, args[1], isDebug);
        interpreter.interpret();


    }
}

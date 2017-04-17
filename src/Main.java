import interpreter.Interpreter;
import scanner.YScanner;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Main {
    /**
     * @param args args[0] program code, args[1] input
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: args[0] program code, args[1] input.");
        }
        YScanner yScanner = new YScanner(args[0]);
        Interpreter interpreter = new Interpreter(yScanner, args[1]);

    }
}

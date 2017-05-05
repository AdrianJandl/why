package am.why.java.interpreter;


import am.why.java.input.InputConverter;
import am.why.java.scanner.YScanner;
import am.why.java.storage.Storage;
import java.util.function.Predicate;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Interpreter {
    private Storage storage;
    private boolean debug;

    public Interpreter(YScanner yScanner, String input) {
        InputConverter inputConverter = new InputConverter();
        storage = inputConverter.process(input);
        while (yScanner.hasNext()) {
            Command next = yScanner.getNextCommand();
            if (next.getSelector() == null && next.getOperator() == null) {
                debug = true;
                continue;
            }
            switch (next.getSelector()) {
                case o:
                case e:
                    doIndexPredicateOperator(next.getSelector().getPredicate(), next.getOperator());
                    break;
                default:
                    doOperator(next.getSelector().getPredicate(), next.getOperator());
            }
            if (debug) {
                System.out.println(storage);
            }
        }
        System.out.println(storage);
    }

    private void doIndexPredicateOperator(Predicate<Object> predicate, Operator operator) {
        Storage integerStorage = storage;
        for (int i = 0; i < integerStorage.getStorage().length; i++) {
            if (predicate.test(i)) {
                integerStorage.getStorage()[i] = operator.getUnaryOperator().apply(integerStorage.getStorage()[i]);
            }
        }
    }


    private void doOperator(Predicate<Object> predicate, Operator operator) {
        Storage integerStorage = storage;
        for (int i = 0; i < integerStorage.getStorage().length; i++) {
            if (predicate.test(integerStorage.getStorage()[i])) {
                integerStorage.getStorage()[i] = operator.getUnaryOperator().apply(integerStorage.getStorage()[i]);
            }
        }
    }

    public Storage getStorage() {
        return storage;
    }
}

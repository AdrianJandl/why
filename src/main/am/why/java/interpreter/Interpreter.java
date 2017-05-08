package am.why.java.interpreter;


import am.why.java.input.InputConverter;
import am.why.java.scanner.YScanner;
import am.why.java.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Interpreter {
    private Storage storage;
    private boolean debug;
    private YScanner yScanner;
    private List<String> history;

    public Interpreter(YScanner yScanner, String input, boolean debug) {
        InputConverter inputConverter = new InputConverter();
        storage = inputConverter.process(input);
        this.yScanner = yScanner;
        this.debug = debug;
        this.history = new ArrayList<>();
    }

    public void interpret() {
        while (yScanner.hasNext()) {
            Command next = yScanner.getNextCommand();
            switch (next.getSelector()) {
                case o:
                case e:
                    doIndexOperator(next.getControlSelector() == null
                            ? next.getSelector().getPredicate()
                            : next.getControlSelector().modifySelector(next.getSelector().getPredicate()),
                            next.getOperator());
                    break;
                default:
                    doOperator(next.getControlSelector() == null
                            ? next.getSelector().getPredicate()
                            : next.getControlSelector().modifySelector(next.getSelector().getPredicate()),
                            next.getOperator());
            }

            if (debug) {
                history.add(storage.toString());
            }
        }
    }

    private void doIndexOperator(Predicate<Object> predicate, Operator operator) {
        for (int i = 0; i < storage.getArray().length; i++) {
            if (predicate.test(i)) {
                storage.getArray()[i] = String.valueOf(operator.getUnaryOperator().apply(storage.getArray()[i]));
            }
        }
    }


    private void doOperator(Predicate<Object> predicate, Operator operator) {
        for (int i = 0; i < storage.getArray().length; i++) {
            if (predicate.test(storage.getArray()[i])) {
                storage.getArray()[i] = String.valueOf(operator.getUnaryOperator().apply(storage.getArray()[i]));
            }
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public List<String> getHistory() {
        return history;
    }
}

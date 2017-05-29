package am.why.java.interpreter;


import am.why.java.exception.YException;
import am.why.java.input.InputConverter;
import am.why.java.scanner.YScanner;
import am.why.java.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        boolean collect = false;
        List<Command> commands = new ArrayList<>();
        while (yScanner.hasNext()) {
            Statement statement = yScanner.getNextCommand();
            if (statement.getType() == Type.COMMAND) {
                Command next = (Command) statement;
                if (collect) {
                    commands.add(next); //TODO history fix?
                    continue;
                }
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
            } else if (statement.getType() == Type.CONTROL) {
                Control control = (Control) statement;
                if (control.getSpecial() == Special.OPEN_BRACKET) {
                    collect = true;
                } else if (control.getSpecial() == Special.CLOSED_BRACKET) {
                    collect = false;
                    Map<Command, List<Integer>> commandListMap = new HashMap<>();
                    for (Command command : commands) {
                        List<Integer> indices = new ArrayList<>();
                        for (int i = 0; i < storage.getArray().length; i++) {
                            if (command.getSelector().getPredicate().test(storage.getArray()[i])) {
                                indices.add(i);
                            }
                        }
                        commandListMap.put(command, indices);
                    }
                    for (Map.Entry<Command, List<Integer>> entry : commandListMap.entrySet()) {
                        for (Integer integer : entry.getValue()) {
                            storage.getArray()[integer] = String.valueOf(entry.getKey().getOperator().getUnaryOperator().apply(storage.getArray()[integer]));
                        }
                    }
                }
            } else {
                throw new YException("Unknown Statement type.");
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

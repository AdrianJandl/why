package am.why.java.interpreter;


import am.why.java.input.InputConverter;
import am.why.java.scanner.YScanner;
import am.why.java.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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
        this.storage = inputConverter.process(input);
        this.yScanner = yScanner;
        this.debug = debug;
        this.history = new ArrayList<>();
    }

    public void interpret() {
        boolean collect = false;
        List<Command> commands = new ArrayList<>();
        while (yScanner.hasNext()) { //iterate over steps
            Step step = yScanner.getNextStep();
            if (step.isSerial()) {
                doSerial(step);
            } else {
                doParallel(step);
            }
        }
    }

    private void doParallel(Step step) {
        Map<Command, List<Integer>> commandListMap = new HashMap<>();
        for (Command next : step.getCommands()) {
            //commands.add(next); //TODO history fix?

            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < storage.getArray().length; i++) {
                if (next.getSelector().getPredicate().test(storage.getArray()[i])) {
                    indices.add(i);
                }
            }
            commandListMap.put(next, indices);
        }
        for (Map.Entry<Command, List<Integer>> entry : commandListMap.entrySet()) {
            for (Integer integer : entry.getValue()) {
                storage.getArray()[integer] = applyOperator(entry.getKey().getOperator(), storage.getArray()[integer], null);
            }
        }
        if (debug) {
            history.add(storage.toString());
        }
    }

    private void doSerial(Step step) {
        for (Command next : step.getCommands()) {
            //commands.add(next); //TODO history fix?
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
        }
    }

    private void doIndexOperator(Predicate<Object> predicate, Operator operator) {
        for (int i = 0; i < storage.getArray().length; i++) {
            if (predicate.test(i)) {
                storage.getArray()[i] = applyOperator(operator, storage.getArray()[i], null);
            }
        }
    }


    private void doOperator(Predicate<Object> predicate, Operator operator) {
        for (int i = 0; i < storage.getArray().length; i++) {
            if (predicate.test(storage.getArray()[i])) {
                storage.getArray()[i] = applyOperator(operator, storage.getArray()[i], null);
            }
        }
    }

    private String applyOperator(Operator op, String data1, String data2){
        Function func = op.getUnaryOperator();
        if (func != null) {
            return applyOperator((UnaryOperator) func, data1);
        } else {
            return applyOperator(op.getBinaryOperator(), data1, data2);
        }
    }

    private String applyOperator(UnaryOperator<Object> op, String data) {
        return String.valueOf(op.apply(data));
    }

    private String applyOperator(BinaryOperator<Object> op, String data1, String optional){
        return String.valueOf(op.apply(data1, optional));
    }


    public Storage getStorage() {
        return storage;
    }

    public List<String> getHistory() {
        return history;
    }
}

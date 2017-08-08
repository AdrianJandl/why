package am.why.java.interpreter;


import am.why.java.input.InputConverter;
import am.why.java.scanner.YScanner;
import am.why.java.storage.Storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

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

    /**
     * Starts the interpreter.
     */
    public void interpret() {
        while (yScanner.hasNext()) { //iterate over steps
            Step step = yScanner.getNextStep();
            if (step.isSerial()) {
                doSerial(step);
            } else {
                doParallel(step);
            }
        }
    }

    /**
     * Executes the {@link Step} in parallel mode.
     *
     * @param step the {@link Step} to be executed.
     */
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

    /**
     * Executes the {@link Step} in serial mode.
     *
     * @param step the {@link Step} to be executed.
     */
    private void doSerial(Step step) {
        for (Command next : step.getCommands()) {
            //commands.add(next); //TODO history fix?
            switch (next.getSelector()) {
                case o:
                case e:
                    doIndexOperator(next.getControlSelector() == null
                                    ? next.getSelector().getPredicate()
                                    : next.getControlSelector().modifySelector(next.getSelector().getPredicate()),
                            next.getOperator(), step.getImmediate(next));
                    break;
                case number:
                    Predicate<Object> predicate = s -> {
                        BigDecimal value = step.getValue(next);
                        return value.compareTo(new BigDecimal(0)) == 0 || Integer.parseInt(String.valueOf(s)) % value.intValue() == 0;
                    };
                    doOperator(next.getControlSelector() == null ? predicate : next.getControlSelector().modifySelector(predicate), next.getOperator(), step.getImmediate(next));
                    break;
                default:
                    doOperator(next.getControlSelector() == null
                                    ? next.getSelector().getPredicate()
                                    : next.getControlSelector().modifySelector(next.getSelector().getPredicate()),
                            next.getOperator(), step.getImmediate(next));
            }
        }
    }

    /**
     * Executes the operator to be applied on the index of the {@link Storage}.
     *
     * @param predicate The {@link Predicate} to be tested.
     * @param operator  The {@link Operator} to be applied.
     * @param immediate An optional {@link BigDecimal} immediate value.
     */
    private void doIndexOperator(Predicate<Object> predicate, Operator operator, BigDecimal immediate) {
        for (int i = 0; i < storage.getArray().length; i++) {
            if (predicate.test(i)) {
                if (immediate != null) {
                    storage.getArray()[i] = applyOperator(operator, storage.getArray()[i], immediate.toPlainString());
                } else {
                    storage.getArray()[i] = applyOperator(operator, storage.getArray()[i], null);
                }
            }
        }
    }

    /**
     * Executes the operator to be applied on the value of the {@link Storage}.
     *
     * @param predicate The {@link Predicate} to be tested.
     * @param operator  The {@link Operator} to be applied.
     * @param immediate An optional {@link BigDecimal} immediate value.
     */
    private void doOperator(Predicate<Object> predicate, Operator operator, BigDecimal immediate) {
        for (int i = 0; i < storage.getArray().length; i++) {
            if (predicate.test(storage.getArray()[i])) {
                if (immediate != null) {
                    storage.getArray()[i] = applyOperator(operator, storage.getArray()[i], immediate.toPlainString());
                } else {
                    storage.getArray()[i] = applyOperator(operator, storage.getArray()[i], null);       // TODO add support for aggregates
                }
            }
        }
    }

    /**
     * Applies the operator on the supplied data.
     *
     * @param op    The {@link Operator} to be applied.
     * @param data1 The data to be operated on.
     * @param data2 Optional data parameter for {@link BinaryOperator}s.
     * @return The data after the {@link Operator} has been applied.
     */
    private String applyOperator(Operator op, String data1, String data2) {
        Function func = op.getUnaryOperator();
        if (func != null) {
            return applyOperator((UnaryOperator) func, data1);
        } else {
            return applyOperator(op.getBinaryOperator(), data1, data2);
        }
    }

    /**
     * Applies the operator on the supplied data.
     *
     * @param op   The {@link Operator} to be applied.
     * @param data The data to be operated on.
     * @return The data after the {@link Operator} has been applied.
     */
    private String applyOperator(UnaryOperator<Object> op, String data) {
        return String.valueOf(op.apply(data));
    }

    /**
     * Applies the operator on the supplied data.
     *
     * @param op       The {@link Operator} to be applied.
     * @param data1    The data to be operated on.
     * @param optional Optional data parameter for {@link BinaryOperator}s.
     * @return The data after the {@link BinaryOperator} has been applied.
     */
    private String applyOperator(BinaryOperator<Object> op, String data1, String optional) {
        return String.valueOf(op.apply(data1, optional));
    }


    public Storage getStorage() {
        return storage;
    }

    public List<String> getHistory() {
        return history;
    }
}

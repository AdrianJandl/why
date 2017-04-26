package interpreter;

import exception.YException;
import input.InputConverter;
import scanner.YScanner;
import storage.IntegerStorage;
import storage.Storage;

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
                case n:
                case p:
                case _0:
                case _1:
                case _2:
                case _3:
                case _4:
                case _5:
                case _6:
                case _7:
                case _8:
                case _9:
                    doOperator(next.getSelector().getPredicate(), next.getOperator());
                    break;
                default:
                    throw new YException("Unrecognized Control character");
            }
            if (debug) {
                System.out.println(storage);
            }
        }
        System.out.println(storage);
    }

    private void doIndexPredicateOperator(Predicate<Integer> predicate, Operator operator) {
        IntegerStorage integerStorage = (IntegerStorage) storage;
        for (int i = 0; i < integerStorage.getStorage().length; i++) {
            if (predicate.test(i)) {
                integerStorage.getStorage()[i] = (Integer) operator.getUnaryOperator().apply(integerStorage.getStorage()[i]);
            }
        }
    }


    private void doOperator(Predicate<Integer> predicate, Operator operator) {
        IntegerStorage integerStorage = (IntegerStorage) storage;
        for (int i = 0; i < integerStorage.getStorage().length; i++) {
            if (predicate.test(integerStorage.getStorage()[i])) {
                integerStorage.getStorage()[i] = (Integer) operator.getUnaryOperator().apply(integerStorage.getStorage()[i]);
            }
        }
    }

}

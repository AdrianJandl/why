package interpreter;

import exception.YException;
import input.InputConverter;
import scanner.YScanner;
import storage.IntegerStorage;
import storage.Storage;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Interpreter {
    private Storage storage;

    public Interpreter(YScanner yScanner, String input) {
        InputConverter inputConverter = new InputConverter();
        storage = inputConverter.process(input);
        while (yScanner.hasNext()) {
            Command next = yScanner.getNextCommand();
            switch (next.getSelector()) {
                case o:
                    doO(next.getOperator());
                    break;
                case e:
                    doE(next.getOperator());
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
                    doO(next.getOperator());
                    break;
                default:
                    throw new YException("Unrecognized Control character");
            }
        }
        System.out.println(storage);
    }

    private void doE(Operator operator) {
        for (int i = 0; i < storage.getStorage().length; i++) {
            if (i % 2 == 0) {
                doOperator(i, operator);
            }
        }
    }

    private void doO(Operator operator) {
        for (int i = 0; i < storage.getStorage().length; i++) {
            if (i % 2 != 0) {
                doOperator(i, operator);
            }
        }
    }

    private void doOperator(int index, Operator operator) {
        switch (operator) {
            case S:
                if (storage instanceof IntegerStorage) {
                    IntegerStorage integerStorage = (IntegerStorage) storage;
                    integerStorage.getStorage()[index] = integerStorage.getStorage()[index] * integerStorage.getStorage()[index];
                }
                break;
            case I:
                if (storage instanceof IntegerStorage) {
                    IntegerStorage integerStorage = (IntegerStorage) storage;
                    integerStorage.getStorage()[index]++;
                }
                break;
            default:
                throw new YException("Unrecognized Operator");
        }
    }

}

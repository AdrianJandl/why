package interpreter;

import exception.YException;
import input.Input;
import scanner.YScanner;
import storage.IntegerStorage;
import storage.Storage;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Interpreter {
    private Storage storage;

    public Interpreter(YScanner yScanner, String input) {
        Input input1 = new Input();
        storage = input1.process(input);
        while (yScanner.hasNext()) {
            Command next = yScanner.getNextCommand();
            switch (next.control) {
                case 'o':
                    doO(next.operator);
                    break;
                case 'e':
                    doE(next.operator);
                    break;
                default:
                    throw new YException("Unrecognized Control character");
            }
        }
        System.out.println(storage);
    }

    private void doE(Character operator) {
        for (int i = 0; i < storage.getStorage().length; i++) {
            if (i % 2 == 0) {
                doOperator(i, operator);
            }
        }
    }

    private void doO(Character operator) {
        for (int i = 0; i < storage.getStorage().length; i++) {
            if (i % 2 != 0) {
                doOperator(i, operator);
            }
        }
    }

    private void doOperator(int index, Character operator) {
        switch (operator) {
            case 'S':
                if (storage instanceof IntegerStorage) {
                    IntegerStorage integerStorage = (IntegerStorage) storage;
                    integerStorage.getStorage()[index] = integerStorage.getStorage()[index] * integerStorage.getStorage()[index];
                }
                break;
            case 'I':
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

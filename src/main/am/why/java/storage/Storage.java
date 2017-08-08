package am.why.java.storage;

import am.why.java.interpreter.Interpreter;
import am.why.java.interpreter.Step;

import java.util.Arrays;

/**
 * This class holds the input array as given and is updated after each {@link Step} by the {@link Interpreter}.
 */
public class Storage {
    private String[] array;

    public Storage(int size) {
        array = new String[size];
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}

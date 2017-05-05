package am.why.java.storage;

import java.util.Arrays;

/**
 * Created by Adrian on 12-Apr-17.
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

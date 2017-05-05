package am.why.java.storage;

import java.util.Arrays;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class IntegerStorage extends Storage {
    private Integer[] storage;

    public IntegerStorage(int size) {
        storage = new Integer[size];
    }

    @Override
    public Integer[] getStorage() {
        return storage;
    }

    @Override
    public String toString() {
        return "main.IntegerStorage{" +
                "main.why.storage=" + Arrays.toString(storage) +
                '}';
    }
}

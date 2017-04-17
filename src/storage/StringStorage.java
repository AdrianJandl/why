package storage;

import java.util.Arrays;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class StringStorage extends Storage {
    private String[] storage;

    public StringStorage(int size) {
        storage = new String[size];
    }

    @Override
    public String[] getStorage() {
        return storage;
    }

    @Override
    public String toString() {
        return "storage.StringStorage{" +
                "storage=" + Arrays.toString(storage) +
                '}';
    }
}

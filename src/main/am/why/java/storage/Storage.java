package am.why.java.storage;

import java.util.Arrays;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Storage {
    private String[] storage;

    public Storage(int size) {
        storage = new String[size];
    }

    public String[] getStorage() {
        return storage;
    }

    public void setStorage(String[] storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }
}

package am.why.java.input;


import am.why.java.storage.Storage;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class InputConverter {

    public Storage process(String input) {
        String[] split = input.split(",");
        Storage storage = new Storage(split.length);
        storage.setArray(split);
        return storage;
    }
}

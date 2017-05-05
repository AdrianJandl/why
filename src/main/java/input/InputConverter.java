package main.java.input;

import main.java.storage.IntegerStorage;
import main.java.storage.Storage;
import main.java.storage.StringStorage;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class InputConverter {

    public Storage process(String input) {
        String[] split = input.split(",");
        Storage stringStorage = new StringStorage(split.length);
        Storage integerStorage = new IntegerStorage(split.length);
        boolean isInt = true;
        for (int i = 0; i < split.length; i++) {
            try {
                integerStorage.getStorage()[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException e) {
                //silently swallow this
                isInt = false;
            }
            stringStorage.getStorage()[i] = split[i];
        }
        //return isInt ? integerStorage : stringStorage;
        return stringStorage;
    }
}

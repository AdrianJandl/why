package input;

import storage.IntegerStorage;
import storage.Storage;
import storage.StringStorage;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Input {

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
        return isInt ? integerStorage : stringStorage;
    }
}

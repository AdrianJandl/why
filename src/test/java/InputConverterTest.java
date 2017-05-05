package java;

import main.java.input.InputConverter;
import org.junit.jupiter.api.Test;
import main.java.storage.IntegerStorage;
import main.java.storage.Storage;
import main.java.storage.StringStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by Adrian on 12-Apr-17.
 */
class InputConverterTest {
    @Test
    void testStringInput() {
        InputConverter inputConverter = new InputConverter();
        Storage storage = inputConverter.process("1,2,a,b,cccc");
        assertTrue(storage instanceof StringStorage);
        assertEquals("1", storage.getStorage()[0]);
        assertEquals("2", storage.getStorage()[1]);
        assertEquals("a", storage.getStorage()[2]);
        assertEquals("b", storage.getStorage()[3]);
        assertEquals("cccc", storage.getStorage()[4]);
    }

    @Test
    void testIntegerInput() {//FIXME how do we handle this?
        InputConverter inputConverter = new InputConverter();
        Storage storage = inputConverter.process("1,2,3,4");
        assertTrue(storage instanceof IntegerStorage);
        assertEquals(1, storage.getStorage()[0]);
        assertEquals(2, storage.getStorage()[1]);
        assertEquals(3, storage.getStorage()[2]);
        assertEquals(4, storage.getStorage()[3]);
        assertEquals(4, storage.getStorage().length);
    }
}

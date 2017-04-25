package test;

import input.InputConverter;
import org.junit.jupiter.api.Test;
import storage.Storage;
import storage.StringStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by Adrian on 12-Apr-17.
 */
public class InputConverterTest {
    @Test
    public void testStringInput(){
        InputConverter inputConverter = new InputConverter();
        Storage storage = inputConverter.process("1,2,a,b,cccc");
        assertTrue(storage instanceof StringStorage);
        assertEquals(storage.getStorage()[0], "1");
        assertEquals(storage.getStorage()[1], "2");
        assertEquals(storage.getStorage()[2], "a");
        assertEquals(storage.getStorage()[3], "b");
        assertEquals(storage.getStorage()[4], "cccc");
    }
}

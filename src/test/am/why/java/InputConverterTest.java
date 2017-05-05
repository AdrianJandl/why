package am.why.java;


import org.junit.Test;

import am.why.java.input.InputConverter;
import am.why.java.storage.IntegerStorage;
import am.why.java.storage.Storage;
import am.why.java.storage.StringStorage;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * Created by Adrian on 12-Apr-17.
 */
public class InputConverterTest {
    @Test
    public void testStringInput() {
        InputConverter inputConverter = new InputConverter();
        Storage storage = inputConverter.process("1,2,a,b,cccc");
        assertTrue(storage instanceof StringStorage);
        assertEquals("1", storage.getStorage()[0]);
        assertEquals("2", storage.getStorage()[1]);
        assertEquals("a", storage.getStorage()[2]);
        assertEquals("b", storage.getStorage()[3]);
        assertEquals("cccc", storage.getStorage()[4]);
    }

    public void testIntegerInput() {//FIXME how do we handle this?
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

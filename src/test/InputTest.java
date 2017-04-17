package test;

import input.Input;
import org.testng.annotations.Test;
import storage.Storage;
import storage.StringStorage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class InputTest {
    @Test
    public void testStringInput(){
        Input input = new Input();
        Storage storage = input.process("1,2,a,b,cccc");
        assertTrue(storage instanceof StringStorage);
        assertEquals(storage.getStorage()[0], "1");
        assertEquals(storage.getStorage()[1], "2");
        assertEquals(storage.getStorage()[2], "a");
        assertEquals(storage.getStorage()[3], "b");
        assertEquals(storage.getStorage()[4], "cccc");
    }
}

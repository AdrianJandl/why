package am.why.java;


import am.why.java.input.InputConverter;
import am.why.java.storage.Storage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Adrian on 12-Apr-17.
 */
public class InputConverterTest {
    @Test
    public void testStringInput() {
        InputConverter inputConverter = new InputConverter();
        Storage storage = inputConverter.process("1,2,a,b,cccc");
        assertEquals("1", storage.getStorage()[0]);
        assertEquals("2", storage.getStorage()[1]);
        assertEquals("a", storage.getStorage()[2]);
        assertEquals("b", storage.getStorage()[3]);
        assertEquals("cccc", storage.getStorage()[4]);
    }

}

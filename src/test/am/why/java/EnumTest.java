package am.why.java;

import org.junit.Test;

import am.why.java.interpreter.Special;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adrian on 21-Apr-17.
 */
public class EnumTest {
    @Test
    public void testConvert() {
        char c = '_';
        Special underScore = Special.UNDERSCORE;
        Special test = Special.from(c);
        assertEquals(underScore, test);
    }
}

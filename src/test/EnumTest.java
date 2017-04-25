package test;

import interpreter.Special;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

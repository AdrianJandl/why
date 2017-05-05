package java;

import main.java.interpreter.Special;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Adrian on 21-Apr-17.
 */
class EnumTest {
    @Test
    void testConvert() {
        char c = '_';
        Special underScore = Special.UNDERSCORE;
        Special test = Special.from(c);
        assertEquals(underScore, test);
    }
}

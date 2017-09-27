package am.why.java.interpreter;

import am.why.java.exception.YException;

import java.math.BigDecimal;
import java.util.function.Predicate;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum Selector {
    e('e'), o('o'), number('_'), n('n'), p('p'), E('E'), O('O');

    private final char aChar;

    Selector(char aChar) {
        this.aChar = aChar;
    }

    /**
     * Converts a {@link Character} to the corresponding {@link Selector}.
     *
     * @param character the {@link Character} to be converted.
     * @return The {@link Selector} instance to the corresponding input character.
     */
    public static Selector from(Character character) {
        for (Selector selector : Selector.values()) {
            if (selector.aChar == character) {
                return selector;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }

    public static Selector[] digits() {
        return new Selector[]{number};
    }

    public Predicate<Object> getPredicate() {
        switch (this) {
            case n:
                return s -> Integer.parseInt(String.valueOf(s)) < 0;
            case p:
                return s -> Integer.parseInt(String.valueOf(s)) > 0;
            case o:
                return s -> Integer.parseInt(String.valueOf(s)) % 2 != 0;
            case e:
                return s -> Integer.parseInt(String.valueOf(s)) % 2 == 0;
            case number:
                return null;
            case E:
                return s -> Integer.parseInt(String.valueOf(s)) % 2 == 0;
            case O:
                return s -> Integer.parseInt(String.valueOf(s)) % 2 != 0;
            default:
                throw new YException("METHOD STUB! NOT YET IMPLEMENTED");
        }
    }
}

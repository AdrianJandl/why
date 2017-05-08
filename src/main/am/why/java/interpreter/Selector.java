package am.why.java.interpreter;

import am.why.java.exception.YException;

import java.util.function.Predicate;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum Selector {
    e('e'), o('o'), _0('0'), _1('1'), _2('2'), _3('3'), _4('4'), _5('5'), _6('6'), _7('7'), _8('8'), _9('9'), n('n'), p('p'), E('E'), O('O');

    private final char aChar;

    Selector(char aChar) {
        this.aChar = aChar;
    }

    public static Selector from(Character character) {
        for (Selector selector : Selector.values()) {
            if (selector.aChar == character) {
                return selector;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }

    public static Selector[] digits() {
        return new Selector[]{_0, _1, _2, _3, _4, _5, _6, _7, _8, _9};
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
            case _0:
                return s -> true;
            case E:
                return s -> Integer.parseInt(String.valueOf(s)) % 2 == 0;
            case O:
                return s -> Integer.parseInt(String.valueOf(s)) % 2 != 0;
            case _1:
                return s -> Integer.parseInt(String.valueOf(s)) == 1;
            case _2:
                return s -> Integer.parseInt(String.valueOf(s)) % 2 == 0;
            case _3:
                return s -> Integer.parseInt(String.valueOf(s)) % 3 == 0;
            case _4:
                return s -> Integer.parseInt(String.valueOf(s)) % 4 == 0;
            case _5:
                return s -> Integer.parseInt(String.valueOf(s)) % 5 == 0;
            case _6:
                return s -> Integer.parseInt(String.valueOf(s)) % 6 == 0;
            case _7:
                return s -> Integer.parseInt(String.valueOf(s)) % 7 == 0;
            case _8:
                return s -> Integer.parseInt(String.valueOf(s)) % 8 == 0;
            case _9:
                return s -> Integer.parseInt(String.valueOf(s)) % 9 == 0;
            default:
                throw new YException("METHOD STUB! NOT YET IMPLEMENTED");
        }
    }
}

package interpreter;

import com.sun.org.apache.bcel.internal.generic.Select;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum Selector {
    e('e'), o('o'), _0('0'), _1('1'), _2('2'), _3('3'), _4('4'), _5('5'), _6('6'), _7('7'), _8('8'), _9('9'), n('n'), p('p');

    private final char c;

    Selector(char c) {
        this.c = c;
    }

    public static Selector from(Character character) {
        for (Selector selector : Selector.values()) {
            if (selector.c == character) {
                return selector;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }

    public static Selector[] digits(){
        return new Selector[]{_0, _1, _2, _3, _4, _5, _6, _7, _8, _9};
    }
}

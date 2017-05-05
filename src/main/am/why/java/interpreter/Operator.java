package am.why.java.interpreter;

import am.why.java.exception.YException;
import java.util.BitSet;
import java.util.function.UnaryOperator;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum Operator {
    S('S'), I('I'), P('P'), U('U'), l('l'), C('C'), f('f'), c('c'), r('r'), pow('^'), b('b'), h('h'), plus('+'), minus('-'), mult('*'), div('/');
    private final char aChar;

    Operator(char aChar) {
        this.aChar = aChar;
    }

    public static Operator from(Character character) {
        for (Operator selector : Operator.values()) {
            if (selector.aChar == character) {
                return selector;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }

    public UnaryOperator<Object> getUnaryOperator() {
        switch (this) {
            case S:
                return i -> ((Integer) i) * ((Integer) i);
            case l:
                return s -> ((String) s).toLowerCase();
            case I:
                return i -> String.valueOf(Integer.parseInt(String.valueOf(i)) + 1);
            case U:
                return s -> ((String) s).toUpperCase();
            case P:
                return s -> isPalindrome(((String) s));
            case r:
                return s -> new StringBuilder(String.valueOf(s)).reverse().toString();
            case f:
                return s -> ""; //FIXME do we change datatype from int to Double/BigDecimal? edit 5.5. mkrejci -> we definitely should
            case b:
                return s -> getBits(s);
            case c:
            case C:
            case h:
            case div:
            case mult:
            case pow:
            case plus:
            case minus:
            default:
                throw new YException("METHOD STUB! \"" + this + "\" NOT YET IMPLEMENTED");
        }
    }

    private String getBits(Object s) {
        try {
            Integer myInt = Integer.parseInt(String.valueOf(s));
            return Integer.toString(myInt, 2);
        } catch (NumberFormatException e) {
            return BitSet.valueOf(String.valueOf(s).getBytes()).toString();
        }
    }

    private String isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return "false";
            }
        }
        return "true";
    }
}

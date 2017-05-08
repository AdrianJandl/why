package am.why.java.interpreter;

import am.why.java.exception.YException;

import java.util.BitSet;
import java.util.function.UnaryOperator;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum Operator {
    S('S'), I('I'), P('P'), U('U'), l('l'), C('C'), f('f'), c('c'), r('r'), pow('^'), b('b'), h('h'), plus('+'), minus('-'), mult('*'), div('/'), D('D');
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
                return i -> {
                    try {
                        return Integer.parseInt(String.valueOf(i)) * Integer.parseInt(String.valueOf(i));
                    } catch (NumberFormatException e) {
                        return i;
                    }
                };
            case l:
                return s -> {
                    try {
                        return ((String) s).toLowerCase();
                    } catch (Exception e) {
                        return s;
                    }
                };
            case I:
                return i -> {
                    try {
                        return Integer.parseInt(String.valueOf(i)) + 1;
                    } catch (NumberFormatException e) {
                        return i;
                    }
                };
            case U:
                return s -> {
                    try {
                        return ((String) s).toUpperCase();
                    } catch (Exception e) {
                        return s;
                    }
                };
            case P:
                return s -> isPalindrome((String.valueOf(s)));
            case r:
                return s -> new StringBuilder(String.valueOf(s)).reverse().toString();
            case f:
                return s -> ""; //FIXME do we change datatype from int to Double/BigDecimal? edit 5.5. mkrejci -> we definitely should
            case b:
                return s -> getBits(s);
            case D:
                return s -> {
                    try {
                        return Integer.parseInt(String.valueOf(s)) - 1;
                    } catch (NumberFormatException e) {
                        return s;
                    }
                };
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
            byte[] bytes = ((String) s).getBytes();
            StringBuilder binary = new StringBuilder();
            for (byte b : bytes) {
                int val = b;
                for (int i = 0; i < 8; i++) {
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
            return binary.toString();
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

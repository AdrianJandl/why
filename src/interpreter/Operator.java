package interpreter;

import exception.YException;

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
                return i -> ((Integer) i) + 1;
            case f:
            case b:
            case c:
            case C:
            case h:
            case P:
            case r:
            case U:
            case div:
            case mult:
            case pow:
            case plus:
            case minus:
            default:
                throw new YException("METHOD STUB! \"" + this + "\" NOT YET IMPLEMENTED");
        }
    }
}

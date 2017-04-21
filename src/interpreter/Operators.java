package interpreter;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum Operators {
    S('S'), I('I'), P('P'), U('U'), l('l'), C('C'), f('f'), c('c'), r('r'), pow('^'), b('b'), h('h'), plus('+'), minus('-'), mult('*'), div('/');
    private final char aChar;

    Operators(char c) {
        this.aChar = c;
    }

    public static Operators from(Character character) {
        for (Operators selector : Operators.values()) {
            if (selector.aChar == character) {
                return selector;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }
}

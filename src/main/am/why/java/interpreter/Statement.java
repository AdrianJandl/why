package am.why.java.interpreter;

import am.why.java.exception.YException;

/**
 * Created by Adrian on 16.05.2017.
 */
public abstract class Statement {
    public abstract Type getType();

    public Selector getSelector() {
        throw new YException("Cannot query Selector");
    }

    public Operator getOperator() {
        throw new YException("Cannot query Operator");
    }
}

package am.why.java.interpreter;

import javax.swing.plaf.nimbus.State;
import java.util.Objects;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Command extends Statement {
    private Selector selector;
    private ControlSelector controlSelector;
    private Operator operator;

    public Command(ControlSelector controlSelector, Selector selector, Operator operator) {
        this.selector = selector;
        this.operator = operator;
        this.controlSelector = controlSelector;
    }

    public Selector getSelector() {
        return selector;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "Command{" +
                "selector=" + selector +
                ", operator=" + operator +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return selector == command.selector &&
                operator == command.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(selector, operator);
    }

    public ControlSelector getControlSelector() {
        return controlSelector;
    }

    @Override
    public Type getType() {
        return Type.COMMAND;
    }
}

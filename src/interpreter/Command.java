package interpreter;

import java.util.Objects;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Command {
    private Selector selector;
    private Operator operator;

    public Command(Selector selector, Operator operator) {
        this.selector = selector;
        this.operator = operator;
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
}

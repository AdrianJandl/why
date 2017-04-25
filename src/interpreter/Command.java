package interpreter;

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
}

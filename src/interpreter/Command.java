package interpreter;

import exception.YException;

import java.util.function.Predicate;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class Command {
    Character control;
    Character operator;

    public Command(Character control, Character operator) {
        this.control = control;
        this.operator = operator;
    }

    public Character getControl() {
        return control;
    }

    public Character getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "interpreter.Command{" +
                "control=" + control +
                ", operator=" + operator +
                '}';
    }

}

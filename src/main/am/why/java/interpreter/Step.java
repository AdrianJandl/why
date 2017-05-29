package am.why.java.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 29-May-17.
 */
public class Step {
    private List<Command> commands;
    private int size = 0;

    public Step(List<Command> commands) {
        this.commands = commands;
    }

    public Step() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        if (command.getControlSelector() != null) {
            size++;
        }
        if (command.getSelector() != null) {
            size++;
        }
        if (command.getOperator() != null) {
            size++;
        }
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        return commands != null ? commands.equals(step.commands) : step.commands == null;
    }

    @Override
    public int hashCode() {
        return commands != null ? commands.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Step{" +
                "commands=" + commands +
                '}';
    }

    public int getSize() {
        return size;
    }
}

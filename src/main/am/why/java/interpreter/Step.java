package am.why.java.interpreter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrian on 29-May-17.
 */
public class Step {
    private List<Command> commands;
    private HashMap<Integer, BigDecimal> immediates;
    private HashMap<Integer, BigDecimal> values;
    private int size = 0;
    private boolean serial = true;

    public Step(List<Command> commands) {
        this();
        this.commands = commands;
    }

    public Step() {
        this.commands = new ArrayList<>();
        this.values = new HashMap<>();
        this.immediates = new HashMap<>();
    }

    /**
     * Adds a {@link Command} to the {@link Step} instance and increments the size of the {@link Step} according to the size of the {@link Command}.
     *
     * @param command the {@link Command} to be added.
     */
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

    /**
     * Adds a {@link Command} to the {@link Step} instance and increments the size of the {@link Step} according to the size of the {@link Command}.
     * Additionally adds the <code>immediate</code> and <code>value</code> {@link BigDecimal}s to the {@link Step} instance.
     *
     * @param command   the {@link Command} to be added.
     * @param immediate the <code>immediate</code> to be added.
     * @param value     the <code>value</code> to be added.
     */
    public void addCommand(Command command, BigDecimal immediate, BigDecimal value) {
        if (value != null) {
            values.put(commands.size(), value);
        }
        if (immediate != null) {
            immediates.put(commands.size(), immediate);
        }
        addCommand(command);
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

    public boolean isSerial() {
        return serial;
    }

    public void setSerial(boolean serial) {
        this.serial = serial;
    }

    public BigDecimal getValue(Command current) {
        return values.get(commands.indexOf(current));
    }

    public BigDecimal getImmediate(Command current) {
        int idx = commands.indexOf(current);
        return immediates.get(idx);
    }

    public BigDecimal getValue(Integer integer) {
        return values.get(integer);
    }

    public BigDecimal getImmediate(Integer integer) {
        return immediates.get(integer);
    }

}

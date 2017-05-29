package am.why.java.interpreter;

/**
 * Created by Adrian on 16.05.2017.
 */
public class Control extends Statement {
    private Special special;

    public Control(Special special) {
        this.special = special;
    }

    public Special getSpecial() {
        return special;
    }

    @Override
    public Type getType() {
        return Type.CONTROL;
    }
}

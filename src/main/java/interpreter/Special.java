package main.java.interpreter;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum Special {
    UNDERSCORE('_');

    private final char aChar;

    Special(char aChar) {
        this.aChar = aChar;
    }

    public static Special from(Character character) {
        for (Special special : Special.values()) {
            if (special.aChar == character) {
                return special;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }

}

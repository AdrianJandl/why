package am.why.java.interpreter;

/**
 * Created by Adrian on 16.05.2017.
 */
public enum Special {
    OPEN_BRACKET('('), CLOSED_BRACKET(')');

    private final char aChar;

    Special(char aChar) {
        this.aChar = aChar;
    }

    /**
     * Converts a {@link Character} to the corresponding {@link Special}.
     *
     * @param character the {@link Character} to be converted.
     * @return The {@link Special} instance to the corresponding input character.
     */
    public static Special from(Character character) {
        for (Special special : Special.values()) {
            if (special.aChar == character) {
                return special;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }
}

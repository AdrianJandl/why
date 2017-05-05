package main.java.interpreter;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum ControlSelector {
    s('s',Selector.digits()), exclamation('!', Selector.values()), l('l',Selector.digits()), L('L',Selector.digits());
    private final char c;
    private final Selector followedBy[];

    ControlSelector(char c, Selector[] followedBy) {
        this.c = c;
        this.followedBy = followedBy;
    }

    public static ControlSelector from(Character character) {
        for (ControlSelector selector : ControlSelector.values()) {
            if (selector.c == character) {
                return selector;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }

    public Selector[] getFollowedBy() {
        return followedBy;
    }
}

package am.why.java.interpreter;

import am.why.java.exception.YException;

import java.math.BigDecimal;
import java.util.function.Predicate;

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

    public Predicate<Object> modifySelector(Predicate<Object> selectorPredicate) {
        switch (this) {
            case exclamation:
                return selectorPredicate.negate();
            case l:
            case L:
            case s:
            default:
                throw new YException("ControlSelector not implemented!");
        }
    }

    public Selector[] getFollowedBy() {
        return followedBy;
    }
}

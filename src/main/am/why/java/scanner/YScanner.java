package am.why.java.scanner;


import am.why.java.exception.YException;
import am.why.java.interpreter.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class YScanner {
    private String program;
    private List<Step> steps;
    private Iterator<Step> stepIterator;

    /**
     * The class responsible for converting the <code>program</code> parameter to a {@link List} of {@link Step}s.
     *
     * @param program the input program
     */
    public YScanner(String program) {
        this.program = program;
        this.steps = new ArrayList<>();
    }

    /**
     * Parses the input program.
     */
    public void parse() {
        Step step = startScanner(0);
        if (step != null && !(step.getCommands().size() == 0)) {
            steps.add(step);
        }
        stepIterator = steps.iterator();
    }

    /**
     * Recursively builds the {@link List} of {@link Step} objects.
     *
     * @param start the index in the program string where this recursion depth starts.
     * @return The next {@link Step} object.
     */
    private Step startScanner(int start) {
        if (start >= program.length()) {
            return null;
        }
        Step current = new Step();
        char a[] = program.toCharArray();
        for (int i = start; i < a.length && a[i] != ')'; i++) {
            Special special;
            try {
                special = Special.from(a[i]);
                if (special == Special.OPEN_BRACKET) {
                    i++;
                    Step step = startScanner(i);
                    if (step != null) {
                        step.setSerial(false);
                        steps.add(step);
                        for (Step tmp : steps) {
                            i += tmp.getSize();
                        }
                    }
                }
                //TODO do we need the third condition? more importantly: what does it do???
                if (special == Special.CLOSED_BRACKET || i + 1 >= a.length || Special.from(a[i + 1]) == Special.CLOSED_BRACKET) {
                    return current;
                }
                i++;
            } catch (Exception e) {
                //swallow this - this only means that current is no Special
            }
            if (i >= a.length) {
                break;
            }
            ControlSelector controlSelector = null;
            try {
                controlSelector = ControlSelector.from(a[i]);
                i++;
            } catch (Exception e) {
                //swallow this - this only means that current is no ControlSelector
            }
            try {
                Selector selector;
                BigDecimal value = null;
                if (Character.isDigit(a[i])) {
                    value = readImmediate(a, i);
                    while (i < a.length && (Character.isDigit(a[i]) || a[i] == '.' || a[i] == '_')) {
                        i++;
                    }
                    selector = Selector.number;
                } else {
                    selector = Selector.from(a[i]);
                    i++;
                }

                if (controlSelector != null) {
                    if (!Arrays.asList(controlSelector.getFollowedBy()).contains(selector)) {
                        throw new YException("Syntax error. Control Selector followed by unallowed Selector at index [" + i + "] in \"" + program + "\"");
                    }
                }

                Operator operators = Operator.from(a[i]);
                i++;
                BigDecimal immediate = null;
                if (i < a.length && Character.isDigit(a[i])) {
                    immediate = readImmediate(a, i);

                    while (i < a.length && (Character.isDigit(a[i]) || a[i] == '.')) {
                        i++;
                    }
                    if (i < a.length && a[i] == '_') {
                        i++;
                    }
                }
                i--;

                current.addCommand(new Command(controlSelector, selector, operators), immediate, value);

            } catch (IllegalArgumentException o_O) {
                throw new YException("Syntax error at index [" + i + "] in \"" + program + "\"");
            }
        }
        return current; //TODO does this mean the bracket wasn't closed?
    }

    private BigDecimal readImmediate(char[] a, int i) {
        BigDecimal value = new BigDecimal(0);

        while (Character.isDigit(a[i])) {
            value = value.multiply(new BigDecimal(10));
            value = value.add(new BigDecimal(Character.digit(a[i], 10)));
            i++;

            if (i == a.length) {
                i--;
                break;
            }
        }
        if (a[i] == '.') {      // decimal point
            int b = -1;
            i++;
            BigDecimal decimalValue;
            while (i < a.length && Character.isDigit(a[i])) {
                decimalValue = new BigDecimal(Character.digit(a[i], 10));
                value = value.add(decimalValue.scaleByPowerOfTen(b));    // rounding mode - nearest neighbour or down if equidistant
                i++;
                b--;
            }
        }


        return value;
    }

    /**
     * Returns the next {@link Step} to be executed.
     *
     * @return the next {@link Step}
     */
    public Step getNextStep() {
        if (stepIterator.hasNext()) {
            return stepIterator.next();
        }
        return null;
    }

    /**
     * Returns <code>true</code> if the underlying {@link Iterator} has more elements.
     *
     * @return true if the iteration has more elements.
     */
    public boolean hasNext() {
        return stepIterator.hasNext();
    }
}

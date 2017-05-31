package am.why.java.scanner;


import am.why.java.exception.YException;
import am.why.java.interpreter.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Adrian on 12-Apr-17.
 */
public class YScanner {
    private String program;
    private boolean syntaxChecked = false;
    private List<Step> steps;
    private Iterator<Step> stepIterator;
    private int bracketCounter;

    public YScanner(String program) {
        this.program = program;
        this.steps = new ArrayList<>();
        this.bracketCounter = 0;
    }

    public void newParse() {
        Step step = startScanner(0);
        if (!(step.getCommands().size() == 0)) {
            steps.add(step);
        }
        stepIterator = steps.iterator();
    }

    public Step startScanner(int start) {
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
                        //i += step.getSize();
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
                Selector selector = Selector.from(a[i]);
                i++;
                if (controlSelector != null) {
                    if (!Arrays.asList(controlSelector.getFollowedBy()).contains(selector)) {
                        throw new YException("Syntax error. Control Selector followed by unallowed Selector at index [" + i + "] in \"" + program + "\"");
                    }
                }
                Operator operators = Operator.from(a[i]);
                //steps.add(new Command(controlSelector, selector, operators));
                current.addCommand(new Command(controlSelector, selector, operators));
            } catch (IllegalArgumentException o_O) {
                throw new YException("Syntax error at index [" + i + "] in \"" + program + "\"");
            }
        }
        return current; //TODO does this mean the bracket wasn't closed?
    }

    public boolean checkSyntax() {
        if (syntaxChecked) {
            throw new YException("Illegal duplicate call to checkSyntax!");
        }
        char a[] = program.toCharArray();
        for (int i = 0; i < a.length; i++) {
            Special special;
            Step step = null;
            try {
                special = Special.from(a[i]);
                if (special == Special.OPEN_BRACKET) {
                    bracketCounter++;
                    //steps.add(new Control(Special.OPEN_BRACKET));
                    //TODO new step
                } else if (special == Special.CLOSED_BRACKET) {
                    bracketCounter--;
                    //steps.add(new Control(Special.CLOSED_BRACKET));
                    //TODO finish step
                }
                if (bracketCounter < 0) {
                    throw new YException("Syntax error at index [" + i + "]. Unexpected character ')'");
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
                Selector selector = Selector.from(a[i]);
                i++;
                if (controlSelector != null) {
                    if (!Arrays.asList(controlSelector.getFollowedBy()).contains(selector)) {
                        throw new YException("Syntax error. Control Selector followed by unallowed Selector at index [" + i + "] in \"" + program + "\"");
                    }
                }
                Operator operators = Operator.from(a[i]);
                //steps.add(new Command(controlSelector, selector, operators));
            } catch (IllegalArgumentException o_O) {
                throw new YException("Syntax error at index [" + i + "] in \"" + program + "\"");
            }
        }
        stepIterator = steps.iterator();
        syntaxChecked = true;
        return true;
    }

    public Step getNextStep() {
        if (stepIterator.hasNext()) {
            return stepIterator.next();
        }
        return null;
    }

    public boolean hasNext() {
        return stepIterator.hasNext();
    }
}

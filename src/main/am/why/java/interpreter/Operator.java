package am.why.java.interpreter;

import am.why.java.exception.YException;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * Created by Adrian on 21-Apr-17.
 */
public enum Operator {
    S('S'), I('I'), P('P'), U('U'), l('l'), C('C'), f('f'), c('c'), r('r'), pow('^'), b('b'), h('h'), plus('+'), minus('-'), mult('*'), div('/'), D('D'), T('T'), t('t'), p('p');
    private final char aChar;

    BigDecimalStringConverter bigDecimalStringConverter = new BigDecimalStringConverter();

    Operator(char aChar) {
        this.aChar = aChar;
    }

    public static Operator from(Character character) {
        for (Operator selector : Operator.values()) {
            if (selector.aChar == character) {
                return selector;
            }
        }
        throw new IllegalArgumentException("No constant " + character + " found.");
    }

    public UnaryOperator<Object> getUnaryOperator() {
        switch (this) {
            case S:
                return i -> {
                    try {
                        return Integer.parseInt(String.valueOf(i)) * Integer.parseInt(String.valueOf(i));
                    } catch (NumberFormatException e) {
                        return i;
                    }
                };
            case l:
                return s -> {
                    try {
                        return ((String) s).toLowerCase();
                    } catch (Exception e) {
                        return s;
                    }
                };
            case I:
                return i -> {
                    try {
                        return Integer.parseInt(String.valueOf(i)) + 1;
                    } catch (NumberFormatException e) {
                        return i;
                    }
                };
            case U:
                return s -> {
                    try {
                        return ((String) s).toUpperCase();
                    } catch (Exception e) {
                        return s;
                    }
                };
            case P:
                return s -> isPalindrome((String.valueOf(s)));
            case r:
                return s -> new StringBuilder(String.valueOf(s)).reverse().toString();
            case b:
                return this::getBits;
            case D:
                return s -> {
                    try {
                        return Integer.parseInt(String.valueOf(s)) - 1;
                    } catch (NumberFormatException e) {
                        return s;
                    }
                };
            case T:
                return s -> System.currentTimeMillis();
            case t:
                return s -> LocalDate.now().toString();
            case C:
                return this::changeCase;
            case p:
                return this::isPrime;
            case f:
            case c:
                return i -> {
                    try {
                        BigDecimal bd = bigDecimalStringConverter.fromString(String.valueOf(i));

                        return this == f ? Math.floor(bd.doubleValue()) : Math.ceil(bd.doubleValue());
                    } catch (NumberFormatException e) {
                        return i;
                    }
                };
            //return s -> ""; //FIXME do we change datatype from int to Double/BigDecimal? edit 5.5. mkrejci -> we definitely should
            case h:
                return this::getHexRepresentation;
            case plus:
            case mult:
            case div:
            case minus:
            case pow:
                return null;
            default:
                throw new YException("METHOD STUB! \"" + this + "\" NOT YET IMPLEMENTED");
        }
    }

    public BinaryOperator<Object> getBinaryOperator() {
        switch (this) {
            case plus:
                return (a, b) -> {
                    try {
                        BigDecimal first = bigDecimalStringConverter.fromString(String.valueOf(a));
                        BigDecimal second = bigDecimalStringConverter.fromString(String.valueOf(b));

                        return first.add(second);
                    } catch (NumberFormatException e) {
                        return a;
                    }
                };
            case mult:
                return (a, b) -> {
                    try {
                        BigDecimal first = bigDecimalStringConverter.fromString(String.valueOf(a));
                        BigDecimal second = bigDecimalStringConverter.fromString(String.valueOf(b));

                        return first.multiply(second);
                    } catch (NumberFormatException e) {
                        return a;
                    }
                };
            case div:
                return (a, b) -> {
                    try {
                        BigDecimal first = bigDecimalStringConverter.fromString(String.valueOf(a));
                        BigDecimal second = bigDecimalStringConverter.fromString(String.valueOf(b));

                        return first.divide(second, 4, BigDecimal.ROUND_HALF_DOWN);
                    } catch (NumberFormatException e) {
                        return a;
                    }
                };
            case minus:
                return (a, b) -> {
                    try {
                        BigDecimal first = bigDecimalStringConverter.fromString(String.valueOf(a));
                        BigDecimal second = bigDecimalStringConverter.fromString(String.valueOf(b));

                        return first.subtract(second);
                    } catch (NumberFormatException e) {
                        return a;
                    }
                };
            case pow:
                return (a, b) -> {
                try {
                    BigDecimal first = bigDecimalStringConverter.fromString(String.valueOf(a));
                    BigDecimal second = bigDecimalStringConverter.fromString(String.valueOf(b));

                    return first.pow(second.intValue());
                } catch (NumberFormatException e) {
                    return a;
                }
            };
            default:
                throw new YException("METHOD STUB! \"" + this + "\" NOT YET IMPLEMENTED");
        }
    }

    private Object getHexRepresentation(Object s) {
        try {
            Integer myInt = Integer.parseInt(String.valueOf(s));
            return Integer.toString(myInt, 16);
        } catch (NumberFormatException e) {
            return String.format("%040x", new BigInteger(1, ((String) s).getBytes(/*YOUR_CHARSET?*/)));
        }
    }

    private String isPrime(Object s) {
        Integer n = Integer.parseInt(String.valueOf(s));
        if (n <= 1) {
            return "false";
        }
        if (n == 2) {
            return "true";
        }
        for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                return "false";
            }
        }
        return "true";
    }

    private String changeCase(Object o) {
        try {
            Integer myInt = Integer.parseInt(String.valueOf(o));
            return myInt.toString();
        } catch (NumberFormatException o_O) {
            String s = String.valueOf(o);
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                String tmp = String.valueOf(c);
                sb.append(tmp.toUpperCase().equals(tmp) ? tmp.toLowerCase() : tmp.toUpperCase());
            }
            return sb.toString();
        }
    }

    private String getBits(Object s) {
        try {
            Integer myInt = Integer.parseInt(String.valueOf(s));
            return Integer.toString(myInt, 2);
        } catch (NumberFormatException e) {
            byte[] bytes = ((String) s).getBytes();
            StringBuilder binary = new StringBuilder();
            for (byte b : bytes) {
                int val = b;
                for (int i = 0; i < 8; i++) {
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
            return binary.toString();
        }
    }

    private String isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return "false";
            }
        }
        return "true";
    }

}

package am.why.java;

import am.why.java.interpreter.ControlSelector;
import am.why.java.interpreter.Selector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


/**
 * Created by Adrian on 08.05.2017.
 */
public class ControlSelectorTest {

    @Test
    public void testAllButSelector() {
        ControlSelector controlSelector = ControlSelector.exclamation;
        Selector selector = Selector._0; //select all
        Predicate<Object> predicate = controlSelector.modifySelector(selector.getPredicate());
        List<Object> test = new ArrayList<>();
        test.add("Hello");
        test.add("World!");
        test.add("3");
        test.add("4");
        test.add("5");
        List<Object> result = test.stream().filter(predicate).collect(Collectors.toList());
        assertEquals(0, result.size());
        selector = Selector.p;
        List<Object> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(-1);
        ints.add(-4);
        predicate = controlSelector.modifySelector(selector.getPredicate());
        List<Object> intResult = ints.stream().filter(predicate).collect(Collectors.toList());
        assertEquals(2, intResult.size());
    }
}

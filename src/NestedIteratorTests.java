import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NestedIteratorTests {

    @Test
    public void testEqual() {

        NestedIterator<Integer> nested = new NestedIterator<>(
                Arrays.asList(1, 4, 7).iterator(),
                Arrays.asList(2, 5, 8).iterator(),
                Arrays.asList(3, 6, 9).iterator());

        List<Integer> results = new ArrayList<>();
        while(nested.hasNext()) {
            results.add(nested.next());
        }

        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), results);

    }

    @Test
    public void testNonEqual() {

        NestedIterator<Integer> nested = new NestedIterator<>(
                Arrays.asList(1, 4, 7).iterator(),
                Arrays.asList(0).iterator(),
                Arrays.asList(3, 6, 9).iterator());

        List<Integer> results = new ArrayList<>();
        while(nested.hasNext()) {
            results.add(nested.next());
        }

        Assert.assertEquals(Arrays.asList(1, 0, 3, 4, 6, 7, 9), results);

    }

    @Test
    public void testEmpty() {

        NestedIterator<Integer> nested = new NestedIterator<>(
                new ArrayList<Integer>().iterator(),
                new ArrayList<Integer>().iterator(),
                new ArrayList<Integer>().iterator());

        List<Integer> results = new ArrayList<>();
        while(nested.hasNext()) {
            results.add(nested.next());
        }

        Assert.assertEquals(Collections.emptyList(), results);

    }
}

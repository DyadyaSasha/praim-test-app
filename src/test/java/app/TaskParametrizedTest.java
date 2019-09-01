package app;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TaskParametrizedTest {

    private Task task;
    private String input;
    private int[] expected;

    public TaskParametrizedTest(String input, int[] expected) {
        this.input = input;
        this.expected = expected;
    }

    @Before
    public void prepareTestFixture() {
        task = new Task();
    }


    @After
    public void clearTestFixture() {
        task = null;
    }

    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][]{
                {"5", null},
                {"4", new int[]{4}},
                {"1;4;2;6;7;3;2;155", new int[]{2, 2, 4, 6}},
                {"23;44;458887;0;99991;-16;555521;999999994;-123;-24;1231;22;135;22;1;-6", new int[]{-24, -16, -6, 0, 22, 22, 44, 999999994}},
                {"5;7;9;11", null},
                {"2;6;2;4;-4;-18", new int[]{-18, -4, 2, 2, 4, 6}},
        });
    }

    @Test
    public void testGetSortedArray() {
        Assert.assertArrayEquals(expected, task.getSortedArray(input));
    }


}

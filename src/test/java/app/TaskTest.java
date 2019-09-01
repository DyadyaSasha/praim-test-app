package app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TaskTest {

    private Task task;


    @Before
    public void prepareTestFixture() {
        task = new Task();
    }


    @After
    public void clearTestFixture() {
        task = null;
    }


    @Test(expected = NullPointerException.class)
    public void testGetSortedArrayThrowsNPE() {
        task.getSortedArray(null);
    }

}

package ie.trusthub.trusthub2;

import junit.framework.TestCase;

/**
 * Created by sean on 25/08/15.
 */
public class TestExample extends TestCase {

    public void test() throws Exception {
        final int expected = 5;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}

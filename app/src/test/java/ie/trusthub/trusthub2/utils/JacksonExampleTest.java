package ie.trusthub.trusthub2.utils;

import junit.framework.TestCase;

/**
 * Created by sean on 25/08/15.
 */
public class JacksonExampleTest extends TestCase {

    public void testJsonFromObject() throws Exception {

        System.out.println("Running testJacksonExampleTest::testJsonFromObject");
        JacksonExample jacksonExample = new JacksonExample();
        ThubUser thubUser = new ThubUser();
        jacksonExample.generateJsonFromObject(thubUser);

        assertEquals(true, true);
    }

    public void testJsonFromString() throws Exception {

        System.out.println("Running testJacksonExampleTest::testJsonFromString");
        JacksonExample jacksonExample = new JacksonExample();
        ThubUser thubUser = jacksonExample.generateJsonFromString("{\"age\":30,\"name\":\"Joe Brown\",\"messages\":[\"msg 111\",\"msg 211\",\"msg 311\"]}");

        assertEquals(thubUser.getName(), "Joe Brown");
    }
}
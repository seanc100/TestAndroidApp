package ie.trusthub.trusthub2.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by sean on 25/08/15.
 */
public class TestJson {


    protected SampleJSON parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
        return new ObjectMapper().readValues(new JsonFactory().createParser(rawJsonData), SampleJSON.class).next();
    }

}

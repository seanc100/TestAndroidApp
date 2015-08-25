package ie.trusthub.trusthub2.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by sean on 25/08/15.
 */
public class JacksonExample {

    public ThubUser generateJsonFromString(String jsonString) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            // read from file, convert it to user class
            ThubUser thubUser = mapper.readValue(jsonString, ThubUser.class);

            // display to console
            System.out.println(thubUser);

            return thubUser;

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }

    public void generateJsonFromObject(ThubUser user) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // display to console JSON
            System.out.println(mapper.writeValueAsString(user));

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}

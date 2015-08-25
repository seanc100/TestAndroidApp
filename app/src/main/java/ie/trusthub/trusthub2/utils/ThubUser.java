package ie.trusthub.trusthub2.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ThubUser {

    @JsonProperty("age")
    private int age = 29;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getMessages() {
        return messages;
    }

    @JsonProperty("name")
    private String name = "Joe Bloggs";

    @JsonProperty("messages")
    private List<String> messages = new ArrayList<String>() {
        {
            add("msg 1");
            add("msg 2");
            add("msg 3");
        }
    };

    //getter and setter methods

    @Override
    public String toString() {
        return "ThubUser [age=" + age + ", name=" + name + ", " +
                "messages=" + messages + "]";
    }
}

package dukeprogram.facilities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Person represents a person in the real world
 */
public class Person implements Serializable {

    private static HashMap<String, Person> existingPersons = new HashMap<>();

    @JsonProperty("name")
    private String name;

    public Person(String name) {
        this.name = name;
    }

    private Person() {

    }

    public String getName() {
        return name;
    }
}

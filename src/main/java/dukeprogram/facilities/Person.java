package dukeprogram.facilities;

import java.io.Serializable;

/**
 * Person represents a person in the real world
 */
public class Person implements Serializable {

    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

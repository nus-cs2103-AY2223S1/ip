package neo;

/**
 * Todo class
 */
public class ToDo extends Task{

    /**
     * todo constructor
     *
     * @param description user input string
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * toString that returns todo task in a specific format.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


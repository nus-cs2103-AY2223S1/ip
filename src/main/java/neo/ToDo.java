package neo;

/**
 * Todo class
 */
public class ToDo extends Task{

    /**
     * Todo constructor.
     *
     * @param description User input string.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns todo task in a specific format.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


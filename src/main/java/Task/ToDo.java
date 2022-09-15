package Task;

/**
 * Represents an object that allows user to describe something they need to do.
 */
public class ToDo extends Task {

    private static final String SHORTHAND = "T";

    public ToDo(String description) {
        super(description, SHORTHAND);
    }

}

package duke;

/**
 * Class to represent a class that will store TodoObjects
 * @author Ashiqur Rahman A02030107Y
 */
public class Todo extends Task {

    /**
     * Constructor for Todo Class
     */
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        String s = String.format("[T]%s", super.toString());
        return s;
    }
}

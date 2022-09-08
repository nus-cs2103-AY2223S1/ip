package duke;

/**
 * A subclass of Task that focuses on processing Todo inputs
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * toString method that turns the input of todo into a String type
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
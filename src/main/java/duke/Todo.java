package duke;

//@@author chengda300
//Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
// with minor modifications

/**
 * Represents a task without deadline.
 */
public class Todo extends Task {
    /**
     * Constructor for a task without deadline.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a task without deadline.
     *
     * @return String representation of a task without deadline.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
//@@author

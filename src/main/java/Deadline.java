/**
 * Represents a Deadline task.
 *
 * @author WR3nd3
 */
public class Deadline extends Task {

    protected String by;
    protected String id = "[D]";

    /**
     * Constructor for the deadline task.
     *
     * @param description String representing the description of the deadline.
     * @param by String representing the time of the deadline.
     * @param isCompleted Boolean representing whether the task is completed.
     */
    public Deadline(String description, String by, Boolean isCompleted) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Returns ListLoader friendly summary of the deadline task.
     *
     * @return String representing summary of the deadline task.
     */
    public  String summary() {
        String status = isCompleted ? "1" : "0";
        String message = "D | " + status + " | " + description + " | " + by;
        return message;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a string consisting of the deadline completion status and description.
     */
    @Override
    public String toString() {
        return id + super.toString() + " (by: " + by + ")";
    }
}
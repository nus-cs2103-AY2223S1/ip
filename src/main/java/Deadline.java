// This class inherits from the abstract Task class
// and encapsulates the logic of a Deadline task.
public class Deadline extends Task {
    /* Due Date field */
    private String dueDate;

    /**
     * Constructor for the Deadline Task.
     * @param description description of the task.
     */
    public Deadline (String description) {
        super(description);
        String[] temp = description.split("/by ");
        this.description = temp[0];
        dueDate = temp.length < 2 ? "No due date given" : temp[1];
    }

    /**
     * Overriden toString method for the Deadline Task.
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}

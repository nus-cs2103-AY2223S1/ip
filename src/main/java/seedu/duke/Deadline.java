package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate dueDate;
    protected String dueTime;

    /**\
     * Represents a Deadline task that has a date but no time included.
     * @param description description of Deadline.
     * @param dueDate Due date of Deadline.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = "";
    }

    /**
     * Represents a Deadline task that has both a due date and due time.
     * @param description description of Deadline.
     * @param dueDate Due date of Deadline.
     * @param dueTime Due time of Deadline.
     */
    public Deadline(String description, LocalDate dueDate, String dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Returns String representation of due date, and due time if it is included.
     * @return String of due date and time.
     */
    public String dueDateToString() {
        String dueDateString = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.dueTime.equals("")) {
            String output = String.format("%s",dueDateString);
            return output;
        } else {
            String output = String.format("%s, %s",dueDateString, this.dueTime);
            return output;
        }
    }

    @Override
    public String toString() {
        String output = String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.dueDateToString());
        return output;
    }
}

package jean.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the task.
     * @param dateTime The deadline of the task.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.by = dateTime;
        super.numberOfTasks += 1;
    }

    /**
     * Returns the String in the format to be saved.
     *
     * @return The formatted String to be saved.
     */
    public String getSaveData() {
        return "D|" + (super.isDone ? 1 : 0) + "|" + super.description + "|"
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the String representation of the Deadline object.
     *
     * @return The formatted String to be displayed.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")");
    }
}

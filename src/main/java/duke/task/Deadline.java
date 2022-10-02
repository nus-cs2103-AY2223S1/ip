package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Task child class that specifies a task with a deadline.
 */
public class Deadline extends Task {

    /**
     * Initialises Deadline object with specified description and date.
     *
     * @param description Description for Deadline object
     * @param by          Date the task is due
     */
    public Deadline(String description, String by) {
        super(description, by, TaskType.DEADLINE);
    }

    @Override
    public String getSaveString() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " | " + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

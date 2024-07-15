package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task that has a deadline (when the task should be finished).
 */
public class Deadline extends Task {

    /**
     * Initializes a Deadline object.
     *
     * @param description The description of the task.
     * @param date The deadline of the task.
     */
    public Deadline(String description, String date) {
        super(description, date);
    }

    /**
     * Returns a string representation of a deadline.
     *
     * @return Details regarding this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + super.getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

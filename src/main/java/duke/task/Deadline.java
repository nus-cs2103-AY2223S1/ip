package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends DatedTask {

    private LocalDateTime by;

    /**
     * Initialises a Deadline with its description and due date.
     * @param description Description of the deadline.
     * @param by Due date of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public LocalDateTime getDate() {
        return by;
    }

    @Override
    public int compareTo(DatedTask task) {
        return this.by.isAfter(task.getDate()) ? 1 : -1;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

}

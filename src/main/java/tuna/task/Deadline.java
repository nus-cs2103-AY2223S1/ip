package tuna.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task. A Deadline object contains the deadline of the task.
 */
public class Deadline extends TimeBasedTask {
    /**
     * Creates a task with a deadline.
     *
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description, "D", LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     * String representation of the Deadline object.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (by: " + parseDateTime(this.getDateTime()) + ")";
    }
}

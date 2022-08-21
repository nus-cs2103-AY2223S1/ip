package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a task with a deadline
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description, "D");
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (by: " + parseDateTime(by) + ")";
    }
}

package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Constructor for Deadline which inherits Task
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs an instance of Deadline which inherits from Task
     * @param description Description of a Deadline task.
     * @param by Date string of the deadline of a task.
     * @return a Deadline object
     * @see Task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE, by);
        this.by = by;
    }

    @Override
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s(by: %s)", "[D]", super.toString(), getBy());
    }
}

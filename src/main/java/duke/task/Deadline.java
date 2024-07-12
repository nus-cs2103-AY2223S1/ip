package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is an extension of Task.
 */
public class Deadline extends Task {
    private String by;
    private String taskType;
    private LocalDate deadline;

    /**
     * Constructor for Deadline.
     *
     * @param description deadline description.
     * @param by due date of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
        this.deadline = LocalDate.parse(by);
    }

    /**
     * Constructor for Deadline.
     *
     * @param description deadline description.
     * @param by due date of deadline.
     * @param priority priority of task.
     */
    public Deadline(String description, String by, String priority) {
        super(description, priority);
        this.by = by;
        this.taskType = "D";
        this.deadline = LocalDate.parse(by);
    }

    /**
     * Returns deadline description.
     *
     * @return deadline description.
     */
    public String getDescription() {
        return super.getDescription() + " | " + by;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (by: %s)",
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}

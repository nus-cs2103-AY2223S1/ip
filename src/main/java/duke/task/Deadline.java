package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    public static final String KEYWORD_TO_SPLIT = " /by ";
    protected LocalDateTime by;

    /**
     * A deadline class which is a task with a date.
     *
     * @param description Description of the task
     * @param by Time of due date
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.by;
    }

    /**
     * Formats a string to be written in the file.
     *
     * @return String to be written
     */
    @Override
    public String getWriteString() {
        return String.format("D | %s | %s", super.getWriteString(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by);
    }
}
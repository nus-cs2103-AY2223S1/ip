package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * A deadline class which is a task with a date.
     *
     * @param description
     * @param by
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
     * @return
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
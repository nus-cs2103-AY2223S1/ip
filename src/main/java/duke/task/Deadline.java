package duke.task;

import java.time.LocalDate;

import duke.common.Utils;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    private final LocalDate by;

    /**
     * Constructor for a new deadline.
     *
     * @param description description of the deadline
     * @param by date the deadline is to be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = Utils.parseDate(by);
    }

    @Override
    public String encodeToString() {
        String taskStatus = isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", taskStatus, description, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getStatusIcon(), description, Utils.convertLocalDate(by));
    }

}

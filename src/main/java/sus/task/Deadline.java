package sus.task;

import java.time.LocalDate;

import sus.common.Utils;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    private final LocalDate dueDate;

    /**
     * Constructor for a new deadline.
     *
     * @param description description of the deadline
     * @param dueDate date the deadline is to be completed by
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String encodeToString() {
        return String.format("D | %s | %s | %s", (isDone ? "1" : "0"), description, dueDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Utils.convertLocalDateToString(dueDate));
    }

}

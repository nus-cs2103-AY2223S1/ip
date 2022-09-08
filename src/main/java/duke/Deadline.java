package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task. A Deadline object corresponds to a task which needs to be done by a specific date
 * e.g. return book by 01-02-2022.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates new Deadline object.
     *
     * @param description Description of task.
     * @param isDone Completion status of task.
     * @param by Deadline of task
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Returns String in format for writing into file.
     *
     * @return storage string.
     */
    @Override
    public String getStorageString() {
        String result = "D | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | "
                + this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (this.getTag() != null) {
            result += " | " + this.getTag();
        }
        return result;
    }

    /**
     * Returns String representation of Deadline object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String result = "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        if (this.getTag() != null) {
            result += " #" + this.getTag();
        }
        return result;
    }
}

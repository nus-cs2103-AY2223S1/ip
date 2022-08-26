package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that is a Deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline.
     * @param by Time it is due by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline.
     * @param by Time it is due by.
     * @param done Status of the task.
     */
    public Deadline(String description, LocalDate by, boolean done) {
        super(description);
        this.by = by;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "+ this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Converts the task into a string representation that can be saved into a file.
     *
     * @return String representation of the task.
     */
    @Override
    public String save() {
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.by.toString();
    }

    /**
     * Returns the time of the task, returning LocalDate.MIN if the task is a ToDo.
     *
     * @return Time of the task.
     */
    @Override
    public LocalDate getTime() {
        return this.by;
    }
}

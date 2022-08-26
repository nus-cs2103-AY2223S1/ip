package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a deadline item
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new Deadline
     * @param description The description of the deadline
     * @param by The date to complete the task by
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(by, formatter);
        this.by = localDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = this.by.format(formatter);
        return "[D]" + super.toString() + String.format(" (by: %s)", date);
    }

    /**
     * Gets the date of the task
     * @return The date of the task
     */
    @Override
    public LocalDate getDate() {
        return this.by;
    }

    /**
     * Gets the task type
     * @return The task type
     */
    @Override
    public String getTaskType() {
        return "D";
    }
}

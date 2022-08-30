package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for deadline task.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Deadline extends Task {
    /**
     * Contains the details of when the task should be completed by.
     */
    protected String by;
    /**
     * Date format of when the task should be completed by.
     */
    protected LocalDate date;

    /**
     * A constructor to initialize Deadline.
     *
     * @param description The details of what the task is about.
     * @param by The details of when the task should be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.date = null;
        }
    }

    @Override
    public String getStringToSave() {
        return this.isDone
                ? "D | 1 | " + description + " | " + by
                : "D | 0 | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return date == null
                ? "[D]" + super.toString() + " (by: " + by + ")"
                : "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

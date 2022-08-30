package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for Event task.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Event extends Task {
    /**
     * Contains the details of when the task will occur.
     */
    protected String at;
    /**
     * Date format of when the task will occur.
     */
    protected LocalDate date;

    /**
     * A constructor to initialize Event.
     *
     * @param description The details of what the task is about.
     * @param at The details of when the task will occur.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            this.date = null;
        }
    }

    @Override
    public String getStringToSave() {
        return this.isDone
                ? "E | 1 | " + description + " | " + at
                : "E | 0 | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return date == null
                ? "[E]" + super.toString() + " (at: " + at + ")"
                : "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

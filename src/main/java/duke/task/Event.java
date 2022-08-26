package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents an event task
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * Constructs an event task
     *
     * @param task_description Description of event task
     * @param date Date of event task
     */
    public Event(String task_description, LocalDate date) {
        super(task_description);
        this.date = date;
    }

    /**
     * Constructs an event task
     *
     * @param task_description Description of event task
     * @param isDone Status of event task
     * @param date Date of event task
     */
    public Event(String task_description, boolean isDone, LocalDate date) {
        super(task_description, isDone);
        this.date = date;
    }

    /**
     * Returns event task
     *
     * @return event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.
                ofPattern("MMM dd yyyy")) + ")";
    }
}

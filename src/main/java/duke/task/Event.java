package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents the event task created by the user.
 */
public class Event extends Task {
    private final LocalDate period;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructor of the Event class.
     * Sets the description of the event and the period the
     * event is occurring to local variables.
     *
     * @param description The description of the event.
     * @param period The period that the event is occurring.
     */
    public Event(String description, LocalDate period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.period.format(dtf) + ")";
    }

    /**
     * Returns the string formatting for the Event task
     * to be stored in the txt file.
     *
     * @return The string formatting of the event
     */
    @Override
    public String stringFormatting() {
        return "E" + super.stringFormatting() + " # " + this.period;
    }
}

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a Task that occurs a given date.
 *
 * @author Edric Yeo
 */
public class Event extends Task {
    protected String at;
    protected LocalDate date;

    /**
     * Constructor for a Event instance with a given date.
     *
     * @param description The description of the Event.
     * @param at          The date that the Event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            this.date = LocalDate.parse(at);
            this.at = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date format! Use a YYYY-MM-DD format!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toDataEntry() {
        int mark = this.isDone ? 1 : 0;
        return String.format("E # %d # %s # %s\n", mark, this.description, this.at);
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a Task that will occur at a specified date.
 */
public class Event extends Task {
    private final char tag = 'E';
    public static final String DELIMITER = " /at ";
    private String time;
    private LocalDate date;

    /**
     * Constructs an instance of Event.
     * @param description
     */
    public Event(String description) {
        super(description.split(Event.DELIMITER)[0].substring(6));
        this.time = description.split(Event.DELIMITER)[1];
        this.date = LocalDate.parse(this.time);
    }

    /**
     * Returns the record of the Event's description, completion status and date of occurrence.
     * @return the record of the Event.
     */
    @Override
    public String printTask() {
        return String.format("[%s]%s (at: %s)",
                this.tag,
                super.printTask(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}

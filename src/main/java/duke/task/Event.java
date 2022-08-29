package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that inherits task
 */
public class Event extends Task {

    protected String at = description.substring(description.lastIndexOf("/") + 4);
    private DateTimeFormatter formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date = LocalDate.parse(at, formatted);

    /**
     * Constructor of Event
     *
     * @param description what the task contains
     */
    public Event(String description) {
        super(description);
    }




    /**
     * Returns the string representation of event
     *
     * @return string that is representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString().substring(0, 4) + getSubstring()
                + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a string containing task to be done (ie. "Go shopping")
     *
     * @return string containing task to be done
     */
    private String getSubstring() {
        int index = description.indexOf("/");
        if (index != -1) {
            return description.substring(6, index);
        }
        return null;
    }
}

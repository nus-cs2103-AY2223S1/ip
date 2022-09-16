package duke.task;

import java.time.LocalDate;
import java.time.Month;

/**
 * Represents a event that has a name and a date that it is on
 */

public class Events extends Task {
    private LocalDate atDate;

    /**
     * Constructor for the Event object
     *
     * @param name
     * @param atDate
     */
    public Events(String name, LocalDate atDate) {
        super(name);
        this.atDate = atDate;
        assert isValidDate() : "Invalid Date time";
    }

    /**
     * Returns a string after method formats the information in event
     *
     * @return String
     */
    @Override
    public String writeData() {
        int mark = isDone ? 1 : 0;
        return "E#" + mark + "#" + this.name + "#" + this.atDate;
    }

    /**
     * Returns a string method that formats the information in event
     *
     * @return String
     */
    @Override
    public String toString() {
        int year = atDate.getYear();
        int day = atDate.getDayOfMonth();
        Month month = atDate.getMonth();
        return "[E]" + super.toString() + " (at: " + day + " " + month + " " + year + ")";
    }

    /**
     * Returns a True if date field is non null
     *
     * @return boolean
     */
    public boolean isValidDate() {
        return this.atDate != null;
    }
}

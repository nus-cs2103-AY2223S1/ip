package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class is an extension to the Task with the functionality to add date
 *
 * @author LimWeiJun
 */
public class Event extends Task {
    LocalDateTime at;

    public Event(String description, boolean done, String at) {
        super(description, done);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getFormattedDetail() + ")";
    }

    @Override
    public char getType() {
        return 'E';
    }

    /**
     * Gets the string of original datetime
     *
     * @return this returns a string
     */
    @Override
    public String getOriginalDetail() {
        return at.toString();
    }

    /**
     * Gets the string of formatted datetime
     *
     * @return returns a string
     */
    @Override
    public String getFormattedDetail() {
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Updates current datetime with a new datetime
     *
     * @param newDateStr of type String
     */
    @Override
    void updateDateTime(String newDateStr) {
        this.at = LocalDateTime.parse(newDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
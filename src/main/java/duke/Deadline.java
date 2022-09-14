package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is an extension to the Task with the functionality to add date
 *
 * @author LimWeiJun
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * A constructor that takes in three parameters
     *
     * @param description of type String
     * @param done of type boolean
     * @param by of type String
     */
    public Deadline(String description, boolean done, String by) {
        super(description, done);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDetail() + ")";
    }

    @Override
    public char getType() {
        return 'D';
    }

    /**
     * Gets the string of original datetime
     *
     * @return this returns a string
     */
    @Override
    public String getOriginalDetail() {
        return by.toString();
    }

    /**
     * Gets the string of formatted datetime
     *
     * @return returns a string
     */
    @Override
    public String getFormattedDetail() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Updates current datetime with a new datetime
     *
     * @param newDateStr of type String
     */
    @Override
    public void updateDateTime(String newDateStr) {
        this.by = LocalDateTime.parse(newDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An extension to the Task with the functionality to add date
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, boolean done, String by) {
        super(description, done);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * @return This returns a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDetail() + ")";
    }

    /**
     * @return this returns a char
     */
    @Override
    public char getType() {
        return 'D';
    }

    /**
     * @return this returns a string
     */
    @Override
    public String getOriginalDetail() {
        return by.toString();
    }

    /**
     * @return this returns a string
     */
    @Override
    public String getFormattedDetail() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
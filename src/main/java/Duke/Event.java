package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class is an extension to the Task with the functionality to add date
 * @author LimWeiJun
 */
public class Event extends Task {
    LocalDateTime at;

    public Event(String description, boolean done, String at) {
        super(description, done);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * @return this returns a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getFormattedDetail() + ")";
    }

    /**
     * @return this returns a char
     */
    @Override
    public char getType() {
        return 'E';
    }

    /**
     * @return this returns a string
     */
    @Override
    public String getOriginalDetail() {
        return at.toString();
    }

    /**
     * @return this returns a string
     */
    @Override
    public String getFormattedDetail() {
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
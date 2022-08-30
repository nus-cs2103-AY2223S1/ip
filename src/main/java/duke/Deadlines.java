package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task to be added to Duke with a deadline.
 */
public class Deadlines extends Task {

    private LocalDateTime dateTime;

    public Deadlines(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns string of formatted deadline.
     *
     * @return Formatted string.
     */
    private String getDateStr() {
        String formattedDateTime = this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return formattedDateTime;
    }

    /**
     * Returns string of data to be saved.
     *
     * @return Data to be saved.
     */
    @Override
    public String savedData() {
        return "D | " + super.savedData() + getDateStr() + "\n";
    }

    /**
     * Returns string for deadline to be displayed on Duke.
     *
     * @return string of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateStr() + ")";
    }
}
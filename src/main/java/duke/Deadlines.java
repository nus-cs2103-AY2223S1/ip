package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task to be added to Duke with a deadline.
 */
public class Deadlines extends Task {

    protected String description;
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
    private String dateStr() {
        String formatDateTime = this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return formatDateTime;
    }

    /**
     * Returns string of data to be saved.
     *
     * @return Data to be saved.
     */
    public String savedData() {
        return "D |" + super.savedData() + dateStr();
    }

    /**
     * Returns string for deadline to be displayed on Duke.
     *
     * @return string of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateStr() + ")";
    }
}
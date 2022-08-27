import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the Deadline task.
     * @param by Date the Deadline task is due by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String formatTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Shows the Deadline task description and the date it is due by.
     *
     * @return String with the Deadline task description and date it is due by.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatTime(by) + ")";
    }
}

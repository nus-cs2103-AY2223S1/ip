import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates the idea of a deadline
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructor for a deadline
     * @param description what is the task
     * @param status whether it has been completed
     * @param date when is the task due
     */
    public Deadline(String description, boolean status, LocalDateTime date) {
        super(description, status);
        this.date = date;
    }

    @Override
    public String getDescription() {
        String status = super.getStatus() ? "T" : "F";
        return "D | " + status + " | " + super.toString() + " | " + date.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.toString() +
                "(by: " + date.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")) + ")";
    }
}

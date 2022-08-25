import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
This class encapsulates the idea of a deadline
 */
public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.toString() +
                "(by: " + date.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")) + ")";
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDateTime by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.by;
    }
}
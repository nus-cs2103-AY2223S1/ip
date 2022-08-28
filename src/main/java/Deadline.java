import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate dateBy;

    public Deadline(String title, boolean done, LocalDate dateBy) {
        super(title, done);
        this.dateBy = dateBy;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

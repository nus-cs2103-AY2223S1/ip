import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM-d-yyyy")) + ")";
    }
    @Override
    public String getSaveString() {
        return "D | " + (isDone ? "1 | " : "0 | " + this.description + " | " + this.by);
    }
}

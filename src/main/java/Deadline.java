import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String saveStringFormat() {
        return String.format("D | %s | %s", super.saveStringFormat(), by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
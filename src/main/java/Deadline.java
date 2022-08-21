import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            LocalDate d1 = LocalDate.parse("2019-12-01");
            byDate = LocalDate.parse(by);
        } catch (DateTimeException e) {

        }
    }

    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString() + " (at: " + byDate.toString() + ")";
        } else {
            return "[D]" + super.toString() + " (at: " + by + ")";
        }
    }
}
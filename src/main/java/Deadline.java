import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        by = by.stripLeading();
        LocalDate ld = LocalDate.parse(by);
        this.by = ld;
    }

    @Override
    public String toString() {
        String form = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + form + ")";
    }
}
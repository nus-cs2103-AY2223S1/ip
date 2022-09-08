package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description, by);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "+ by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

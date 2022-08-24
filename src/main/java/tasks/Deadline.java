package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final DateTimeFormatter ACCEPT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TO = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, ACCEPT);
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " BY " + this.by.format(TO);
    }
}

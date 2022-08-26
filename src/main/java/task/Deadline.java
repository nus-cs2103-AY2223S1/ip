package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String stringify() {
        return String.format("D##%s##%s", super.stringify(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}

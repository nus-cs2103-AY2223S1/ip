package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/[uuuu][uu] HHmm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern(("d/M/yyyy HHmm"));
    private final LocalDateTime deadline;
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = LocalDateTime.parse(deadline, Deadline.formatter);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(Deadline.printFormatter) + ")";
    }
}

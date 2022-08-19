package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    private String printDate() {
        return DateTimeFormatter.ofPattern("MMM dd yyyy").format(by);
    }

    @Override
    public String stringify() {
        return String.format("%s | %s | %s", "D", super.stringify(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.printDate());
    }
}

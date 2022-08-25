package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toData() {
        return "D" + super.toData() + this.time.toString();
    }
}

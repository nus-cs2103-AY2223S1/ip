package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate returnBy;

    public Deadline(String description, LocalDate returnBy) {
        this.description = description;
        this.isDone = false;
        this.returnBy = returnBy;
    }

    public Deadline(String description, boolean isDone, LocalDate returnBy) {
        this.description = description;
        this.isDone = isDone;
        this.returnBy = returnBy;
    }

    @Override
    public String saveStringFormat() {
        return String.format("D | %d | %s | %s", this.isDone? 1 : 0, this.description,
                this.returnBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toString() {
        return "[D] " + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.returnBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

    }
}

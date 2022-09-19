package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected LocalDateTime completeBy;

    public Deadline(String description, LocalDateTime completeBy) {
        super(description, false);
        this.completeBy = completeBy;
    }

    public Deadline(String description, boolean isDone, String completeBy) {

        super(description, isDone);
        this.completeBy = LocalDateTime.parse(completeBy,
                DateTimeFormatter.ofPattern("d/M/y HHmm"));
    }

    @Override
    public String fileString() {

        return "D" + super.fileString() + " | " +
                completeBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " +
                completeBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";

    }

}
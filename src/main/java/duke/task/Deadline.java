package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String stringToSave() {
        return "D|" + ("X".equals(super.getStatusIcon()) ? "1|" : "0|") + super.description + "|" + this.deadline;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Deadline extends Task {

    protected LocalDateTime completeBy;

    Deadline(String description, LocalDateTime completeBy) {
        super(description, false);
        this.completeBy = completeBy;
    }

    Deadline(String description, boolean isDone, LocalDateTime completeBy) {

        super(description, isDone);

        this.completeBy = completeBy;

    }

    @Override
    public String fileString() {

        return "D" + super.fileString();
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " +
                completeBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";

    }

}
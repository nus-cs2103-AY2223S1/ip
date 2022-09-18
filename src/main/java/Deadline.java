import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Deadline extends Task {

    private LocalDateTime completeBy;

    Deadline(String description, String completeBy) {
        super(description, false);
        this.completeBy = LocalDateTime.parse(completeBy,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    Deadline(String description, boolean isDone, String completeBy) {

        super(description, isDone);

        this.completeBy = LocalDateTime.parse(completeBy,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

    }

    @Override
    public String toFileString() {

        return "D" + super.toFileString();
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " +
                completeBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";

    }

}
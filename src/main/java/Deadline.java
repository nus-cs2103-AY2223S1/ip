import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Deadline extends Task {

    private LocalDateTime completeBy;

    Deadline(String description, String completeBy) {
        super(description, false);
        this.completeBy = LocalDateTime.parse(completeBy,
                DateTimeFormatter.ofPattern("[d/M/y HHmm]"));;
    }

    Deadline(String description, boolean isDone, String completeBy) {

        super(description, isDone);
        this.completeBy = LocalDateTime.parse(completeBy,
                DateTimeFormatter.ofPattern("[d/M/y HHmm]"));;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " +
                completeBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

}
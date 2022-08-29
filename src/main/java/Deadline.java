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
<<<<<<< HEAD
        this.completeBy = completeBy;
=======
        this.completeBy = LocalDateTime.parse(completeBy,
                DateTimeFormatter.ofPattern("[d/M/y HHmm]"));;
>>>>>>> branch-Level-8
    }

    @Override
    public String toFileString() {

        return "D | " + (this.isDone ? 1 : 0) + " | " +
                this.description + " | " + this.completeBy;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " +
                completeBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

}
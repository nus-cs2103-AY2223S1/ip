import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String deadline;
    protected LocalDate time;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.time = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toWrite() {
        return String.format("D~%s~%s~%s", (isDone ? "1" : "0"), description.trim(), deadline);
    }

}

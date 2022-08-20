import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    String by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy | E | h:mma");
        LocalDateTime dateTime = LocalDateTime.parse(by.trim(), inputFormatter);

        this.by = dateTime.format(outputFormatter);

    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toStringData() {
        return "D | " + super.toStringData() + " | " + by;
    }
}

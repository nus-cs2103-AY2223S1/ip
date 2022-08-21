import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Deadline represents a Task with a deadline represented as a single LocalDateTime object.
 * Inherits from Task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    @Override
    public String toString() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT));
        return String.format("[D] %s (by: %s)", super.toString(), formattedBy);
    }
}

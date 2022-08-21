import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    @Override
    public String toString() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT.STRICT));
        return String.format("[D] %s (by: %s)", super.toString(), formattedBy);
    }
}

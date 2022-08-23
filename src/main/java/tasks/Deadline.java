package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Tasks.Deadline represents a Tasks.Task with a deadline represented as a single LocalDateTime object.
 * Inherits from Tasks.Task.
 */
public class Deadline extends Task {
    static final String DEADLINE_DATETIME_FORMAT = "d/MM/uuuu HHmm";

    protected LocalDateTime by;

    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    public Deadline(String taskDescription, boolean isComplete, LocalDateTime by) {
        super(taskDescription);
        this.setIsComplete(isComplete);
        this.by = by;
    }

    @Override
    public String getEncodedValue() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT));
        return String.format("[D]#%s#%s#%s", getTaskDescription(), getIsComplete(), formattedBy);
    }

    @Override
    public String toString() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT));
        return String.format("[D] %s (by: %s)", super.toString(), formattedBy);
    }
}

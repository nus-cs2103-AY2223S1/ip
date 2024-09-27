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

    /**
     * Overloaded constructor for Deadline.
     *
     * @param taskDescription Description of Deadline.
     * @param by              LocalDateTime representation of the deadline.
     */
    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Overloaded constructor for Deadline.
     *
     * @param taskDescription Description of Deadline.
     * @param isComplete      Whether the Deadline is completed.
     * @param by              LocalDateTime representation of the deadline.
     */
    public Deadline(String taskDescription, boolean isComplete, LocalDateTime by) {
        super(taskDescription);
        this.setIsComplete(isComplete);
        this.by = by;
    }

    /**
     * Returns the encoded representation of the current Deadline to be saved to an external storage.
     *
     * @return String representation of current Deadline for saving to Storage.
     */
    @Override
    public String getEncodedValue() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm")
                .withResolverStyle(ResolverStyle.STRICT));
        return String.format("[D]#%s#%s#%s", getTaskDescription(), getIsComplete(), formattedBy);
    }

    /**
     * Returns string representation of Deadline to be printed to terminal.
     *
     * @return Returns string representation of Deadline to be printed to terminal.
     */
    @Override
    public String toString() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm")
                .withResolverStyle(ResolverStyle.STRICT));
        return String.format("[D] %s (by: %s)", super.toString(), formattedBy);
    }
}

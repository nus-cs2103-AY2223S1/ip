package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a Task that contains a deadline to be completed by.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for deadline.
     *
     * @param isDone boolean denoting whether Deadline should be marked.
     * @param text description of task.
     * @param deadline date and time for Deadline to be completed by.
     */
    public Deadline(boolean isDone, String text, LocalDateTime deadline) {
        super(isDone, text);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)",
                super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")));
    }
}

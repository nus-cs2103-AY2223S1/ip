package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private LocalDateTime deadline;

    /**
     * Initialises a deadline object.
     *
     * @param task     descirption of the deadline
     * @param deadline time of the deadline
     */
    public Deadlines(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                this.getDone()
                        ? "X"
                        : " ", this.getTask(), this.deadline.format(DateTimeFormatter.ofPattern("MMM, d, YYYY")));
    }
}

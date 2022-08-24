import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /**
     * The deadline of Deadline
     */
    private LocalDate deadline;

    /**
     * Constructor for Deadline
     * @param title The title of Deadline
     * @param isDone The isDone status of the Task
     * @param deadline The deadline of Deadline
     */
    public Deadline(String title, boolean isDone, LocalDate deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}

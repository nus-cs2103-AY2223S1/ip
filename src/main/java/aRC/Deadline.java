package arc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /**
     * The deadline of aRC.Deadline
     */
    private LocalDate deadline;

    /**
     * Constructor for aRC.Deadline
     * @param title The title of aRC.Deadline
     * @param isDone The isDone status of the aRC.Task
     * @param deadline The deadline of aRC.Deadline
     */
    public Deadline(String title, boolean isDone, LocalDate deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    @Override
    public String fileFormat() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.title, this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}

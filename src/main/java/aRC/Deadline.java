package arc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Deadline task
 */
public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructor for aRC.Deadline
     * @param title The title of aRC.Deadline
     * @param isDone The isDone status of the aRC.Deadline
     * @param deadline The deadline of aRC.Deadline
     */
    public Deadline(String title, boolean isDone, LocalDate deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns how a Deadline should be represented
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    /**
     * Returns how a Deadline should be stored in a txt file
     * @return String representation of Deadline
     */
    @Override
    public String toFileFormat() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.title,
                this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}

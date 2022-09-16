package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline - a task with a due date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a new Deadline.
     *
     * @param description Description of deadline.
     * @param by Due date of deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns whether the deadline is on the specified date.
     *
     * @param date Specified date.
     * @return whether the deadline is on the specified date.
     */
    public boolean isOn(LocalDate date) {
        return this.by.equals(date);
    }

    /**
     * Returns file representation of Deadline.
     *
     * @return "D | {1 if done else 0} | {description} | {formattedDueDate}".
     */
    @Override
    public String toFileRepresentation() {
        return String.format("D | %s | %s", super.toFileRepresentation(), this.by);
    }

    /**
     * Returns new Deadline from file representation.
     *
     * @param rep String of file representation.
     * @return Deadline instance.
     */
    public static Deadline fromFileRepresentation(String rep) {
        String[] args = rep.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String description = args[2];
        String date = args[3];
        Deadline result = new Deadline(description, LocalDate.parse(date));
        if (isDone) {
            result.markDone();
        }
        return result;
    }

    /**
     * Returns String representation of Deadline.
     *
     * @return "[D] {[X] if done else [ ]} | {description} (by: {formattedBy})"
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")));
    }
}

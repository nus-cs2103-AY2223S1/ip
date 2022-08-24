package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that encapsulates the Deadline task.
 */
public class Deadline extends Task {

    /** The deadline of the task */
    private final LocalDate deadline;

    /**
     * The class constructor.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the string to be saved in the text file to be
     * loaded in later.
     *
     * @return The string identifying the details of the task.
     */
    @Override
    public String stringToSave() {
        return "D|" + ("X".equals(super.getStatusIcon()) ? "1|" : "0|") + super.description + "|" + this.deadline;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

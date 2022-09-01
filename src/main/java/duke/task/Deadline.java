package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a deadline task.
 */
public class Deadline extends Task {
    public LocalDate by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of this deadline.
     * @param isDone Boolean indicating whether this task is done.
     * @param by LocalDate indicating the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the string format of this deadline task to be saved in the save file.
     *
     * @return String representation of this deadline task in the format it is saved in the save file.
     */
    public String toFileFormat() {
        String isDone = this.isDone ? "1" : "0";
        return "D | " + isDone + " | " + this.description + " | " + this.by;
    }

    /**
     * String representation of this deadline.
     *
     * @return String representing this deadline.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns a boolean indicating if this deadline is the same as the obj.
     *
     * @param obj Object to be compared to.
     * @return True if this deadline and obj are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline tmp = (Deadline) obj;
            return super.equals(tmp) && this.by.equals(tmp.by);
        }
        return false;
    }

}

package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The deadline class encapsulates a deadline.
 */
public class Deadline extends Task{
    private LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns string representation of the type and isDone attributes of a Deadline object.
     *
     * @return String representation of the status of a Deadline object.
     */
    @Override
    public String getStatus() {
        if(this.isDone) {
            return "[D][X]";
        } else {
            return "[D][ ]";
        }
    }

    /**
     * Returns string representation of a Deadline object.
     *
     * @return String representation of a Deadline object.
     */
    @Override
    public String toString() {
        return this.getStatus() + " " + this.description + " (by: " +
                this.time.format(DateTimeFormatter.ofPattern("hh:mm a 'on' dd/MM/yyyy")) + ")";
    }

    /**
     * Returns true if object passed is of type Deadline and has the same description as the Deadline object.
     *
     * @param obj Object to be compared with.
     * @return Whether object passed and the Deadline object are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline dl = (Deadline) obj;
            return this.description.equals(dl.description) && this.time.equals(dl.time);
        }
        return false;
    }
}

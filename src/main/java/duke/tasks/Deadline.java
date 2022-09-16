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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline dl = (Deadline) obj;
            return this.description.equals(dl.description) && this.time.equals(dl.time);
        }
        return false;
    }
}

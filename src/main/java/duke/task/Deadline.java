package duke.task;

import java.time.LocalDate;

/**
 * Represents the Deadline Task object.
 */
public class Deadline extends Task {
    public static final String DELIMITER = " /by ";

    private LocalDate time;

    /**
     * Constructs a new Deadline object from description and time.
     * @param description Description for the Deadline object.
     * @param time Time for the Deadline object.
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a new Deadline object from description, time and isDone status.
     * @param description Description for the Deadline object.
     * @param time Time for the Deadline object.
     * @param isDone IsDone status for the Deadline object.
     */
    public Deadline(String description, LocalDate time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Gets the string representation of the Deadline object.
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }

    /**
     * String representation of the Deadline object to be saved to and loaded from.
     * @return String representation of the Deadline object to be saved.
     */
    @Override
    public String toSave() {
        return "D" + Task.SAVE_SEPARATOR + this.getIsDoneString() + Task.SAVE_SEPARATOR + this.getDescription()
                + Task.SAVE_SEPARATOR + this.time;
    }

    /**
     * Checks if an object is equal to this Deadline object.
     * @param obj Object to be checked against.
     * @return Whether the object is equal to this Deadline object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        boolean doesDescriptionMatch = this.getDescription().equals(other.getDescription());
        boolean doesTimeMatch = this.time.equals(other.time);
        return doesDescriptionMatch && doesTimeMatch;
    }
}

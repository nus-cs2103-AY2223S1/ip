package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Deadline object, a task with a specific completion date
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline object with description and completion date
     *
     * @param description name or details of deadline
     * @param by date to be completed by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * updates either name or date of Deadline Task with updateInfo
     *
     * @param updateInfo info to update for Deadline Task
     * @throws DateTimeParseException if date format is incorrect
     */
    @Override
    public void updateTask(String updateInfo) throws DateTimeParseException {
        if (updateInfo.startsWith("/")) {
            LocalDate updateDate = LocalDate.parse(updateInfo.substring(1));
            this.by = updateDate;
        } else {
            this.description = updateInfo;
        }
    }

    /**
     * Returns the save format of the Deadline object
     *
     * @return String representing how Deadline object is saved
     */
    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }

    /**
     * Returns the string representation of the Deadline object
     *
     * @return String representation of Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

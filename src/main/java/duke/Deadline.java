package duke;

//import java.lang.NumberFormatException;
import java.time.LocalDate;

/**
 * Deadline class representing user deadlines.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a Deadline object with the provided description and deadline.
     * @param description A description of the task related to this deadline.
     * @param deadline A date which represents the deadline for this task.
     * @throws DukeException Displays an error message to the user if the wrong date format is used.
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        this.deadline = processDate(deadline);
    }

    private LocalDate processDate(String deadline) throws DukeException {
        String[] dateInfo = deadline.split("-");
        if (dateInfo.length < 3) {
            throw new DukeException("Please use format yyyy-mm-dd for your deadline!");
        }
        try {
            LocalDate toReturn = LocalDate.of(Integer.valueOf(dateInfo[0]),
                    Integer.valueOf(dateInfo[1]),
                    Integer.valueOf(dateInfo[2]));
            return toReturn;
        } catch (NumberFormatException e) {
            throw new DukeException("Please use format yyyy-mm-dd for your deadline, and only use numbers!");
        }
    }

    /**
     * Changes the description of this Deadline.
     * @param newDesc The new description of this Deadline.
     */
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Changes the date of this Deadline.
     * @param newDate The new date for this Deadline.
     */
    public void setDeadline(String newDate) throws DukeException {
        this.deadline = processDate(newDate);
    }

    /**
     * Returns a String representation of this deadline, with details such as its description and date.
     * @return A String representation of this Deadline.
     */
    @Override
    public String toString() {
        return "  [D] [" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }
}

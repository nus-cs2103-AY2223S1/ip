package duke;

import java.time.LocalDate;
import java.lang.NumberFormatException;

public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Creates a Deadline object with the provided description and deadline.
     * @param description A description of the task related to this deadline.
     * @param deadline A date which represents the deadline for this task.
     * @throws DukeException Displays an error message to the user if the wrong date format is used.
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        String[] dateInfo = deadline.split("-");
        if (dateInfo.length < 3) {
            throw new DukeException("Please use format yyyy-mm-dd for your deadline!");
        }
        try {
            this.deadline = LocalDate.of(Integer.valueOf(dateInfo[0]), Integer.valueOf(dateInfo[1]), Integer.valueOf(dateInfo[2]));
        } catch (NumberFormatException e) {
            throw new DukeException("Please use format yyyy-mm-dd for your deadline, and only use numbers!");
        }
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

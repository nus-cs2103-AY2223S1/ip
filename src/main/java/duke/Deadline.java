package duke;

import java.time.LocalDate;

public class Deadline extends Task {

    // Attributes of a duke.Deadline
    protected LocalDate byDate;
    protected String byTime;


    /**
     * The information regarding the duke.Deadline
     * @param description the description of the task to do by the deadline
     * @param byDate the due date
     * @param byTime the due time
     * @throws DukeException self created exception
     */
    public Deadline(String description, LocalDate byDate, String byTime) throws DukeException {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
        if (description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    /**
     * The string representation of the deadline
     * @return a string representing the description and the due date of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime.toString() + ")";
    }

    /**
     * A string representation to write to the file
     * @return a string that represents what to write to the file
     */
    public String saveToDisk() {
        String alreadyDone = super.getStatusIcon();
        return "D | " + alreadyDone + " | " + super.getDescription() + "\n";
    }
}

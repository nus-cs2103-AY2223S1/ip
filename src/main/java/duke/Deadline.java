package duke;

import java.time.LocalDate;

/**
 * This class is responsible for creating and manipulating Deadlines
 *
 * @author Kang Zong Xian
 */
public class Deadline extends Task {

    // Attributes of a Deadline
    protected LocalDate byDate;
    protected String byTime;


    /**
     * The constructor for the Deadline task
     * @param description the description of the task to do by the deadline
     * @param byDate the due date of the deadline task
     * @param byTime the due time of the deadline task
     * @throws DukeException the exception to be thrown
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
     * A string representation of the deadline task to be saved
     * @return a string representing the description and the due date of the deadline task to be saved
     */
    public String saveToDisk() {
        String alreadyDone = super.getStatusIcon();
        return "D | " + alreadyDone + " | " + super.getDescription() + "\n";
    }
}

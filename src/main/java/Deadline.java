import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class inherits from Task
 * Deadline objects are tasks with deadlines
 * @author Nam Minh Quan
 */

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, String deadline) throws DukeException{
        super(description);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (deadline.equals("")) {
            throw new DukeException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
        }
        this.deadline = new DukeDate().convertToDate(deadline);
    }
    /**
     * Sets a new deadline for the task
     * @param newDeadline new deadline for Event
     */
    public void setDeadline(String newDeadline) {
        this.deadline = new DukeDate().convertToDate(newDeadline);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by:" + this.deadline.format(formatter) + ")";
    }
}

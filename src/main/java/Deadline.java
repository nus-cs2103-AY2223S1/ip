import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class inherits from Task
 * Deadline objects are tasks with deadlines
 * @author Nam Minh Quan
 */

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, String deadline, boolean done) throws DukeException{
        super(description, done);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (deadline.equals("")) {
            throw new DukeException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
        }
        this.deadline = new DukeDate(deadline).getDateTime();
    }

    /**
     * Sets a new deadline for the task
     * @param newDeadline new deadline for Event
     */
    public void setDeadline(String newDeadline) {
        this.deadline = new DukeDate(newDeadline).getDateTime();
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public boolean isTodo() {
        return false;
    };
    @Override
    public boolean isDeadline() {
        return true;
    };
    @Override
    public boolean isEvent() {
        return false;
    };
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[D]" + super.toString() + " (by:" + this.deadline.format(formatter) + ")";
    }
}

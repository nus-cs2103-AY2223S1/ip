/**
 * Deadline class inherits from Task
 * Deadline objects are tasks with deadlines
 * @author Nam Minh Quan
 */

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) throws DukeException{
        super(description);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (deadline.equals("")) {
            throw new DukeException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
        }
        this.deadline = deadline;
    }
    /**
     * Sets a new deadline for the task
     * @param deadline new deadline for Event
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }
}

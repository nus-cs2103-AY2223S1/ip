/**
 * Deadline class inherits from Task
 * Deadline objects are tasks with deadlines
 * @author Nam Minh Quan
 */

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline, boolean done) throws DukeException{
        super(description, done);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (deadline.equals("")) {
            throw new DukeException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
        }
        this.deadline = deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
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
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }
}

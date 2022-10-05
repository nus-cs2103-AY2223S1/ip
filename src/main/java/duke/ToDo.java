package duke;

/**
 * Deadline class which is a subclass of Event.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String saveTaskAsString() {
        String status = this.isDone ? "1" : "0";
        return String.format("T | %s | %s", status, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
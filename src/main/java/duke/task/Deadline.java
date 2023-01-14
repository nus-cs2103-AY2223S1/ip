package duke.task;

/** A class representing a type of task that has a deadline, in the form of a date. */
public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String convertToFile() {
        return "deadline," + this.description + " /by " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        numberOfTasks++;
    }

    @Override
    public String addedString() {
        return "Got it. I've added this task:\n " + this.toString() + "\nNow you have " + this.numberOfTasks + " tasks in the list.";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
// Deadline a child class of Task has the same functionality
// but adds on with a by field which allows users to set a deadline

public class Deadline extends Task {

    protected String by;

    //Constructor
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // toString method to change the display for different types of tasks on the console
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

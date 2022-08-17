// This class inherits from the abstract Task class
// and encapsulates the logic of a Deadline task.
public class Deadline extends Task {

    private String dueDate;

    public Deadline (String description) {
        super(description);
        String[] temp = description.split("/by ");
        this.description = temp[0];
        dueDate = temp.length < 2 ? "No due date given" : temp[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}

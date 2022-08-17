/**
 * The Deadline class is a subclass of Task. It represents
 * a task in real life that has a deadline associated.
 */
public class Deadline extends Task {
    public String date;

    public Deadline(String taskName) throws IndexOutOfBoundsException {
        super(taskName.substring(9, taskName.indexOf(" /by")));
        this.date = taskName.substring(taskName.indexOf(" /by") + 5);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}

/**
 * Class used to represent a Deadline type task that has a due date.
 */
public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String taskName, boolean isDone, String dueDate) {
        super(taskName, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}

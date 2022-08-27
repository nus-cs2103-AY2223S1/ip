package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns this <code>Task</code> in CSV format.
     *
     * @return CSV representation of this Task
     */
    @Override
    public String toCsv() {
        return "T," + super.toCsv() + "\n";
    }

    /**
     * Returns a string representation of this <code>Task</code>.
     *
     * @return a string representation of this <code>Task</code>
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

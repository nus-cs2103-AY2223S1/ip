package duke;

/**
 * Encapsulates a Task without a deadline or duration.
 */
public class ToDo extends Task {
    /**
     * Constructor.
     *
     * @param name
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns String representation of the ToDo object.
     *
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Deadline || task instanceof Event) {
            return 1;
        } else {
            return this.getName().compareTo(task.getName());
        }
    }
}

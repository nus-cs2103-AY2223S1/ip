package duke.tasks;

/**
 * Task that represents Todo Task.
 */
public class TodoTask extends Task {
    /**
     * Default constructor of the Todo Task.
     *
     * @param name Name of the Todo Task.
     */
    public TodoTask(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     * @return String representation of the Todo Task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.T + "]" + "[" + this.getStatusIcon() + "] " + this.getName();
    }

    /**
     * Return the String representation of the object in CSV.
     * The following attributes are saved.
     * Type of task - D,E,T.
     * Marked status - X," ".
     * Name.
     *
     * @return String representation of Todo Task in CSV.
     */
    @Override
    public String toCsv() {
        return TaskType.T + "," + this.getStatusIcon() + "," + this.getName();
    }
}

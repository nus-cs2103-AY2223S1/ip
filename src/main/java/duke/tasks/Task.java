package duke.tasks;

/**
 * Abstract class that represents Task.
 */
public abstract class Task {
    private boolean marked;
    private String name;

    /**
     * Default constructor of the abstract Task class.
     *
     * @param name Name of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }
    public String getName() {return this.name;}

    /**
     * Returns the status icon of the Task.
     * If it is marked, return X. Else return empty string with a space.
     *
     * @return Status icon of the Task.
     */
    public String getStatusIcon() {
        return (marked ? "X" : " "); // mark done task with X
    }

    /**
     * Changes the marked status of Task to true.
     */
    public void markAsDone() {
        this.marked = true;
    }

    /**
     * Changes the marked status of Task to false.
     */
    public void unmark() {
        this.marked = false;
    }

    /**
     * Return the String representation of the object in CSV.
     * The following attributes are saved.
     * Type of task - D,E,T.
     * Marked status - X," ".
     * Name.
     * Any other additional information.
     *
     * @return String representation of Task in CSV.
     */
    public abstract String toCSV();
}

package duke.tasks;

/**
 * Task that represents Events.
 */
public class EventTask extends Task {
    private String time;

    /**
     * Default constructor of the Events Task.
     *
     * @param name Name of the task.
     * @param time Time of the task.
     */
    public EventTask(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    /**
     * {@inheritDoc}
     * @return String representation of the Events Task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + "[" + this.getStatusIcon() + "] " + this.getName()
                + " (at: " + this.getTime() + ")";
    }

    /**
     * Return the String representation of the object in CSV.
     * The following attributes are saved.
     * Type of task - D,E,T.
     * Marked status - X," ".
     * Name.
     * Time of the task.
     *
     * @return String representation of Events Task in CSV.
     */
    @Override
    public String toCsv() {
        return TaskType.E + "," + this.getStatusIcon() + "," + this.getName() + "," + this.getTime();
    }
}

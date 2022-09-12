package alpha.task;

/**
 * Represents event type of task.
 */
public class Event extends Task {

    /** Event task date */
    protected String date;

    /**
     * Constructor to initialise the global variables.
     *
     * @param description To initialise the task description.
     * @param date To initialise the date of the event task.
     * @param taskType To initialise the task type.
     */
    public Event(String description, String date, String taskType) {
        super(description, taskType);
        this.date = date;
    }

    /**
     * Returns the event task date.
     *
     * @return Event task date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the date in red font colour along with the other details of the task.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (on: %s)", date);

    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects.
     * Returns true if both objects are instance of Event class and have the same attributes.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event) {
            Event e = (Event) obj;
            return (e.getDescription().equals(this.getDescription()) && e.getStatus().equals(this.getStatus())
                    && e.getTaskType().equals(this.getTaskType()) && e.getDate().equals(this.getDate()));
        }
        return false;
    }
}

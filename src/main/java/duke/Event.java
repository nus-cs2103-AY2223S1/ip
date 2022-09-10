package duke;

/**
 * Represents an event task, or an event from the user. It is a type of task, and hence
 * inherits from <code>Task</code> class.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event Task.
     *
     * @param taskName the description of the event.
     * @param isDone   the completion status of the event.
     * @param at       time of the event.
     */
    public Event(String taskName, boolean isDone, String at, int priority) {
        super(taskName, isDone, priority);
        this.at = at;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "]" + "[" +
                this.getPriority() + "] " + this.taskName + " (at: " + this.at + ")";
    }

    /**
     * Returns the string representation of the task which is saved locally, for easy retrieval.
     *
     * @return string representation of event task which is saved.
     */
    @Override
    public String toStore() {
        return "|E|" + "|" + this.getStatusIcon() + "|" + "|" + this.getPriority() + "| "
                + this.taskName + " (at: " + this.at + ")";
    }
}

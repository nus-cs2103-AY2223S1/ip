package task;

/**
 * Represents a more specific task with time limit attached to it. A <code>Event</code> object corresponds to
 * a task with a time range e.g., <code>Aug 6th 2-4pm</code>
 */
public class Event extends Task {
    private String at;

    /**
     * Constructor for Event.
     *
     * @param name      Name of the task.
     * @param completed The status of the task. (Completed or not)
     * @param at        The deadline.
     */
    public Event(String name, boolean completed, String at) {
        super(name, completed);
        this.at = at;
    }

    /**
     * Returns the Event's time.
     *
     * @return Time of event.
     */
    @Override
    public String getTime() {
        return at;
    }

    /**
     * Returns the type of task.
     *
     * @return Type of task.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Toggles the completion of event task.
     *
     * @return A toggled version of event. (Completed = true -> Completed = false)
     */
    @Override
    public Task toggleCompleted() {
        return new Event(getName(), !isCompleted(), at);
    }

    /**
     * Converts event to a string.
     *
     * @return A string that represent a event. E.g. [E][X] get a book /at Aug 6th 2-4pm.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", checkMarked(), getName(), at);
    }
}

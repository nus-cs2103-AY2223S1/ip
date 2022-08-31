package duke;

import java.time.LocalDate;

/**
 * Class used to represent a task that has a start date.
 */
public class Event extends Task {
    protected LocalDate eventDate;

    /**
     * The constructor for an Event.
     *
     * @param taskName A string that is the name of the task.
     * @param isDone A boolean that represents whether this task is complete.
     * @param eventDate A LocalDate that contains information about the start date of this task.
     */
    public Event(String taskName, boolean isDone, LocalDate eventDate) {
        super(taskName, isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String toSaveFormatString() {
        return String.format("E|%d|%s|%s", isDone ? 1 : 0, taskName, eventDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventDate);
    }
}

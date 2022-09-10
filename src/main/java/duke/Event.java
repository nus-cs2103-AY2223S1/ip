package duke;

import java.time.LocalDate;

/**
 * Class used to represent a task that has a start date.
 */
public class Event extends Task {
    public static final String TASK_TYPE_CHARACTER = "E";

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
        return String.format("%s|%d|%s|%s", TASK_TYPE_CHARACTER, isDone ? 1 : 0, taskName, eventDate);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", TASK_TYPE_CHARACTER, super.toString(), eventDate);
    }
}

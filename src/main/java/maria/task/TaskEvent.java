package maria.task;

import java.time.LocalDate;

import maria.util.DukeDateTimeFormatter;

/**
 * Represents a Task of type Event.
 */
public class TaskEvent extends Task {

    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Creates an Event Task.
     * @param name The name of the task
     * @param isDone Whether the task is completed
     * @param startTime The starting time for the event
     * @param endTime The ending time for the event
     * @throws TaskNoNameException If the name is empty
     */
    public TaskEvent(String name, boolean isDone, LocalDate startTime, LocalDate endTime)
            throws TaskNoNameException {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the string representation of the task.
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[Event] " + super.toString() + " (from "
                + DukeDateTimeFormatter.formatDisplay(this.startTime) + " to "
                + DukeDateTimeFormatter.formatDisplay(this.endTime) + ")";
    }

    /**
     * Gets the storage string representation of the task.
     * @return The storage string representation of the task
     */
    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "event" + "|||"
                + DukeDateTimeFormatter.formatStorage(this.startTime) + "|||"
                + DukeDateTimeFormatter.formatStorage(this.endTime);
    }

}

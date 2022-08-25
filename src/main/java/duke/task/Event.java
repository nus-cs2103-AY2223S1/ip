package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task with TaskType, description,
 * boolean to check whether the task is done, and time of deadline.
 */
public class Event extends Task {

    private LocalDateTime atTime;

    /**
     * Constructor for Event
     *
     * @param taskType TaskType of event
     * @param name Description of the event
     * @param isMarked Denotes whether the event is done yet
     * @param timeStr Time of event
     */
    public Event(TaskType taskType, String name, boolean isMarked, String timeStr) {
        super(taskType, name, isMarked);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "HHmm, d/MM/yyyy");
        this.atTime = LocalDateTime.parse(timeStr, formatter);
    }

    /**
     * @return Time of event
     */
    public LocalDateTime getAtTime() {
        return this.atTime;
    }
    /**
     * {@inheritDoc}
     * @return String of event with details, TaskType, name, isMarked and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "hh:mm a, EEE, d MMM yyyy");
        return "[E]" + super.toString()
                + " (at: " + atTime.format(formatter) + ")";
    }
}

package duke.models.task;

import java.time.LocalDate;

import duke.models.serializable.TaskSerializable;
import duke.utils.DukeFormatter;

/**
 * Encapsulates a {@link Task} that starts at a specific date and ends at a specific date.
 *
 * @author Emily Ong Hui Qi
 */

public class Event extends Task {
    private static final TaskType taskType = TaskType.EVENT;
    protected LocalDate date;

    /**
     * Initializes the Event task with the provided description and date.
     *
     * @param description The received description.
     * @param date        The received date.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Initializes the Event task with the provided description, date and completion status.
     *
     * @param description The received description.
     * @param date        The received date.
     * @param isDone      The received completion status.
     * @param doneAt      The received done at date.
     */
    public Event(String description, LocalDate date, boolean isDone, LocalDate doneAt) {
        super(description, isDone, doneAt);
        this.date = date;
    }

    @Override
    public String getTaskTypeIcon() {
        return Event.taskType.toString();
    }

    @Override
    public TaskSerializable serialize() {
        return new TaskSerializable(Event.taskType, super.description, super.isDone, super.doneAt, this.date);
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), DukeFormatter.formatDate(this.date));
    }
}

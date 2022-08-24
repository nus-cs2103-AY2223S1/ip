package duke.models.task;

import java.time.LocalDate;

import duke.models.serializable.TaskSerializable;
import duke.utils.DukeFormatter;

/**
 * Encapsulates a task that starts at a specific time and ends at a specific time,
 * e.g., team project meeting on 2/10/2019 2-4pm
 *
 * @author Emily Ong Hui Qi
 */

public class Event extends Task {
    private static final TaskType taskType = TaskType.EVENT;
    protected LocalDate datetime;

    /**
     * TODO: Add JavaDocs
     */
    public Event(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * TODO: Add JavaDocs
     */
    public Event(String description, LocalDate datetime, boolean isDone) {
        super(description, isDone);
        this.datetime = datetime;
    }

    @Override
    public String getTaskTypeIcon() {
        return Event.taskType.toString();
    }

    @Override
    public TaskSerializable serialize() {
        return new TaskSerializable(Event.taskType, super.description, super.isDone, this.datetime);
    }

    @Override
    public LocalDate getDate() {
        return this.datetime;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), DukeFormatter.formatDate(this.datetime));
    }
}

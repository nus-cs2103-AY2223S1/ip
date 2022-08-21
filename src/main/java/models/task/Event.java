package models.task;

import models.serializable.TaskSerializable;
import utils.DukeFormatter;

import java.time.LocalDate;

/**
 * Encapsulates a task that starts at a specific time and ends at a specific time,
 * e.g., team project meeting on 2/10/2019 2-4pm
 *
 * @author Emily Ong Hui Qi
 */

public class Event extends Task {
    protected LocalDate datetime;
    private static final TaskType taskType = TaskType.EVENT;

    public Event(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

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

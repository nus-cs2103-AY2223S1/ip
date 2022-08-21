package models.task;

import models.serializable.TaskSerializable;
import utils.DukeFormatter;

import java.time.LocalDate;

/**
 * Encapsulates a task that needs to be done before a specific date/time,
 * e.g., submit report by 11/10/2019 5pm
 *
 * @author Emily Ong Hui Qi
 */

public class Deadline extends Task {
    protected LocalDate deadline;
    private static final TaskType taskType = TaskType.DEADLINE;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return Deadline.taskType.toString();
    }

    @Override
    public TaskSerializable serialize() {
        return new TaskSerializable(Deadline.taskType, super.description, super.isDone, this.deadline);
    }

    @Override
    public LocalDate getDate() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), DukeFormatter.formatDate(this.deadline));
    }
}

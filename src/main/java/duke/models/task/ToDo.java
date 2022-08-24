package duke.models.task;

import java.time.LocalDate;

import duke.models.serializable.TaskSerializable;

/**
 * Encapsulates a task without any date/time attached to it, e.g. visit new theme park
 *
 * @author Emily Ong Hui Qi
 */

public class ToDo extends Task {
    private static final TaskType taskType = TaskType.TODO;

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public TaskSerializable serialize() {
        return new TaskSerializable(ToDo.taskType, super.description, super.isDone, null);
    }

    @Override
    public String getTaskTypeIcon() {
        return ToDo.taskType.toString();
    }

    @Override
    public LocalDate getDate() {
        return null;
    }
}

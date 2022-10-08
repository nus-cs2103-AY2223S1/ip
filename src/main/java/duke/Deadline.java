package duke;

import java.time.LocalDate;

/**
 * Class used to represent a task that has a due date.
 */
public class Deadline extends Task {
    public static final String TASK_TYPE_CHARACTER = "D";

    protected LocalDate dueDate;

    /**
     * The constructor for a Deadline task.
     *
     * @param taskName A string that is the name of the task.
     * @param isDone A boolean that represents whether this task is complete.
     * @param dueDate A LocalDate that contains information about when this task is due.
     */
    public Deadline(String taskName, boolean isDone, LocalDate dueDate) {
        super(taskName, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toSaveFormatString() {
        return String.format("%s|%d|%s|%s", TASK_TYPE_CHARACTER, isDone ? 1 : 0, taskName, dueDate);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", TASK_TYPE_CHARACTER, super.toString(), dueDate);
    }
}

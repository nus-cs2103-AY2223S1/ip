package duke;

import java.time.LocalDate;

/**
 * Class used to represent a task that has a due date.
 */
public class Deadline extends Task {
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
        return String.format("D|%d|%s|%s", isDone ? 1 : 0, taskName, dueDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}

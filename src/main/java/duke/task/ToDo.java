package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * ToDo is a subclass of Task indicates task to be done in the future.
 */
public class ToDo extends Task {
    private static final String TASK_TYPE = "T";

    public ToDo(String taskName, LocalDate date, LocalTime time) {
        super(taskName, date, time);
    }

    public ToDo(String taskName, boolean markDone, LocalDate date, LocalTime time) {
        super(taskName, markDone, date, time);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public ToDo mark() {
        return new ToDo(super.getTaskName(), true, super.getDate(), super.getTime());
    }

    public ToDo unmark() {
        return new ToDo(super.getTaskName(), false, super.getDate(), super.getTime());
    }

    @Override
    public String toString() {
        //return "[" + this.getTaskType() + "]" + super.toString();
        return this.getTaskType() + "," + super.toString();
    }
}

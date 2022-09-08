package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline is a subclass of Task indicates task to be expired.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "D";

    public Deadline(String taskName, LocalDate date, LocalTime time) {
        super(taskName, date, time);
    }

    public Deadline(String taskName, boolean markDone, LocalDate date, LocalTime time) {
        super(taskName, markDone, date, time);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public Deadline mark() {
        return new Deadline(super.getTaskName(), true, super.getDate(), super.getTime());
    }

    public Deadline unmark() {
        return new Deadline(super.getTaskName(), false, super.getDate(), super.getTime());
    }

    @Override
    public String toString() {
        return this.getTaskType() + "," + super.toString();
    }
}

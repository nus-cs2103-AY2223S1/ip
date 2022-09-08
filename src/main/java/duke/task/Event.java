package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event is a subclass of Task indicates task to be attended.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "E";

    public Event(String taskName, LocalDate date, LocalTime time) {
        super(taskName, date, time);
    }

    public Event(String taskName, boolean markDone, LocalDate date, LocalTime time) {
        super(taskName, markDone, date, time);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public Event mark() {
        return new Event(super.getTaskName(), true, super.getDate(), super.getTime());
    }

    public Event unmark() {
        return new Event(super.getTaskName(), false, super.getDate(), super.getTime());
    }

    @Override
    public String toString() {
        return this.getTaskType() + "," + super.toString();
    }
}

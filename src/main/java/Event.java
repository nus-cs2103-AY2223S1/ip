import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    private static final String TASK_TYPE = "E";

    Event(String taskName, LocalDate date, LocalTime time) {
        super(taskName, date, time);
    }

    Event(String taskName, boolean markDone, LocalDate date, LocalTime time) {
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
        return "[" + this.getTaskType() + "]" + super.toString();
    }
}

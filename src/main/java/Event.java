public class Event extends Task {
    private static final String TASK_TYPE = "E";

    Event(String taskName) {
        super(taskName);
    }

    Event(String taskName, boolean markDone) {
        super(taskName, markDone);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public Event mark() {
        return new Event(super.getTaskName(), true);
    }

    public Event unmark() {
        return new Event(super.getTaskName(), false);
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString();
    }
}

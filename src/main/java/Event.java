public class Event extends Task {
    private String date;
    private final String TASK_TYPE = "E";

    public Event(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    public Event(String taskName, boolean hasCompleted, String date) {
        super(taskName, hasCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + "(at:" + date + ")";
    }

    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString() + "|" + date;
    }
}

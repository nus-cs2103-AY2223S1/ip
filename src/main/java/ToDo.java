public class ToDo extends Task {
    private final String TASK_TYPE = "T";

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, boolean hasCompleted) {
        super(taskName, hasCompleted);
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString();
    }
}

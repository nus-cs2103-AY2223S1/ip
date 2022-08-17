public class ToDo extends Task {
    private static final String TASK_TYPE = "T";

    ToDo(String taskName) {
        super(taskName);
    }

    ToDo(String taskName, boolean markDone) {
        super(taskName, markDone);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public ToDo mark() {
        return new ToDo(super.getTaskName(), true);
    }

    public ToDo unmark() {
        return new ToDo(super.getTaskName(), false);
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString();
    }
}

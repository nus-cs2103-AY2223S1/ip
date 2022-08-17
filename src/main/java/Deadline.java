public class Deadline extends Task {
    private static final String TASK_TYPE = "D";

    Deadline(String taskName) {
        super(taskName);
    }

    Deadline(String taskName, boolean markDone) {
        super(taskName, markDone);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public Deadline mark() {
        return new Deadline(super.getTaskName(), true);
    }

    public Deadline unmark() {
        return new Deadline(super.getTaskName(), false);
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString();
    }
}

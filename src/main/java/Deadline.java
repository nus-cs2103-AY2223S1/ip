public class Deadline extends Task{
    private String dueDate;
    private final String TASK_TYPE = "D";

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    public Deadline(String taskName, boolean hasCompleted, String dueDate) {
        super(taskName, hasCompleted);
        this.dueDate = dueDate;
    }


    @Override
    public String toString() {
        return "[" + TASK_TYPE +"]" + super.toString() + "(by:" + dueDate + ")";
    }

    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString() + "|" + dueDate;
    }
}

package duke.task;

public class TodoTask extends Task {

    private static final String LABEL = "T";

    protected TodoTask(String taskTitle) {
        super(taskTitle, TaskType.TODO);
    }

    @Override
    public String toString() {
        return super.getStringRepresentation(LABEL, super.taskTitle);
    }

    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation(LABEL);
    }
}

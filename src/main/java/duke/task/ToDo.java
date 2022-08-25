package duke.task;

public class ToDo extends Task {
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription);
        this.isDoneSetter(isDone);
    }

    @Override
    protected String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toStorageString() {
        return "T" + "|" + super.toStorageString();
    }
}

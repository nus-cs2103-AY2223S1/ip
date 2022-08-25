package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String taskInfo() {
        return "[T] [" + getStatusIcon() + "] " + description;
    }

    @Override
    public String taskSaveInfo() {
        return "T," + getSavedStatusIcon() + "," + description;
    }
}

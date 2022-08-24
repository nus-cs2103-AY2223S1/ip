package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String TaskInfo() {
        return "[T] [" + getStatusIcon() + "] " + description;
    }

    @Override
    public String TaskSaveInfo() {
        return "T," + getSavedStatusIcon() + "," + description;
    }
}

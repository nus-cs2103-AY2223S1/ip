package duke.task;

/**
 * Encapsulates a task without any date/time attached to it.
 */
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

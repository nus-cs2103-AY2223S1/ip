package duke.task;

import duke.task.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T" + Task.SAVE_SEPARATOR + this.getIsDoneString() + Task.SAVE_SEPARATOR + this.getDescription();
    }
}

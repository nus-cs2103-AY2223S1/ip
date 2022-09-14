package duke.commands;

import duke.task.TaskList;

public abstract class Command {
    static final int DISPLAYED_INDEX_OFFSET = 1;

    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    void setIsExitToTrue() {
        isExit = true;
    }

    public abstract String execute(TaskList taskList);
}

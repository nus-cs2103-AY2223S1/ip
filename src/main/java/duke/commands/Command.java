package duke.commands;

import duke.task.TaskList;

public abstract class Command {
    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    void setIsExitToTrue() {
        isExit = true;
    }

    public abstract String execute(TaskList taskList);
}

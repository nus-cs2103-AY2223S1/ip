package commands;

import tasklist.TaskList;

abstract public class Command {
    protected boolean exit = false;

    public abstract void execute(TaskList taskList);

    public boolean isExit() {
        return this.exit;
    }
}

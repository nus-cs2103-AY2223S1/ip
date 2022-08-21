package command;

import henry.TaskList;

public class Command {

    protected TaskList taskList;

    public CommandResult execute() {
        throw new UnsupportedOperationException("Cannot execute abstract command!");
    }

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }
}

package duke.command;

import duke.task.Task;

public abstract class UpdateCommand extends Command {
    protected Task task;
    protected int taskIndex;  // stores 0-based index of task in duke.TaskList

    public UpdateCommand(String command, Task task, int taskIndex) {
        super(command);
        this.task = task;
        this.taskIndex = taskIndex;
    }
}

package duke.commands;

import duke.task.TaskList;

public abstract class TaskListCommand extends Command {
    protected final TaskList taskList;
    protected static final String taskListText = "Now you have %s tasks in the list.";

    protected String taskCountText() {
        return String.format(taskListText, taskList.taskCount());
    }
    public TaskListCommand(String invoker, TaskList taskList) {
        super(invoker);
        this.taskList = taskList;
    }
}

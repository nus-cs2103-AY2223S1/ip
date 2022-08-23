package duke.commands;

import duke.task.TaskList;

public abstract class TaskListCommand extends Command {
    protected static final String taskListText = "Now you have %s tasks in the list.";
    protected final TaskList taskList;

    public TaskListCommand(String invoker, TaskList taskList) {
        super(invoker);
        this.taskList = taskList;
    }

    protected String taskCountText() {
        return String.format(taskListText, taskList.taskCount());
    }
}

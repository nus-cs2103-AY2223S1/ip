package duke.commands;

import duke.task.TaskList;

public class ListCommand extends TaskListCommand {
    private static final String noTasksText = "Good job! You have no outstanding tasks.";
    private static final String tasksText = "Here are the tasks in your list:";

    public ListCommand(String invoker, TaskList taskList) {
        super(invoker, taskList);
    }

    @Override
    public String execute(String parameters) {
        return taskList.taskCount() == 0
                ? noTasksText
                : tasksText + "\n" + taskList.listTasks();
    }
}

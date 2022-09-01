package duke.commands;

import duke.task.TaskList;

/**
 * The command used to enumerate and display the items in a TaskList.
 */
public class ListCommand extends TaskListCommand {

    /**
     * Text to display when there are no outstanding tasks.
     */
    private static final String NO_TASKS_TEXT = "Good job! You have no outstanding tasks.";
    /**
     * Text to display before enumerating the tasks in the list.
     */
    private static final String tasksText = "Here are the tasks in your list:";

    public ListCommand(String invoker, TaskList taskList) {
        super(invoker, taskList);
    }

    /**
     * Lists the contents of the items in the associated TaskList.
     *
     * @param parameters Command arguments, unused.
     * @return The enumerated contents of the list delimited by a newline character.
     */
    @Override
    public String execute(String parameters) {
        return taskList.taskCount() == 0
                ? NO_TASKS_TEXT
                : tasksText + "\n" + taskList.enumerateTasks();
    }
}

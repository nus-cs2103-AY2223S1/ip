package duke.commands;

import duke.task.TaskList;

/**
 * A command for finding tasks that match certain strings.
 */
public class FindCommand extends TaskListCommand {

    private static final String successText = "Here are the matching tasks in your list:";
    private static final String failureText = "No matching tasks found.";

    /**
     * Constructor for a TaskListCommand.
     *
     * @param invoker  The string used to invoke the execution of this command.
     * @param taskList The TaskList that this command adds a task to.
     */
    public FindCommand(String invoker, TaskList taskList) {
        super(invoker, taskList);
    }

    /**
     * Finds and outputs the list of tasks satisfying the string query.
     *
     * @param parameters Command arguments, used as a search key.
     * @return List output of all tasks that match the query.
     */
    @Override
    public String execute(String parameters) {
        String toSearch = parameters.toLowerCase();
        TaskList tempTaskList = new TaskList();
        taskList.findInList(toSearch).forEach(tempTaskList::addTask);
        return tempTaskList.taskCount() > 0
                ? successText + "\n" + tempTaskList.listTasks()
                : failureText;

    }
}

package duke.commands;

import duke.task.TaskList;

/**
 * An abstract class of commands that interact with a TaskList.
 * Provides some common functionality such as providing the message for displaying list length.
 */
public abstract class TaskListCommand extends Command {

    /**
     * The string that is formatted when TaskListCommand::taskCountText is called.
     */
    protected static final String TASK_LIST_TEXT = "Now you have %s tasks in the list.";
    protected final TaskList taskList;

    /**
     * Constructor for a TaskListCommand.
     *
     * @param invoker  The string used to invoke the execution of this command.
     * @param taskList The TaskList that this command adds a task to.
     */
    public TaskListCommand(String invoker, TaskList taskList) {
        super(invoker);
        this.taskList = taskList;
    }

    /**
     * Provides a formatted version of the TaskList length for display.
     *
     * @return Formatted text showing the number of tasks in the list.
     */
    protected String taskCountText() {
        return String.format(TASK_LIST_TEXT, taskList.taskCount());
    }
}

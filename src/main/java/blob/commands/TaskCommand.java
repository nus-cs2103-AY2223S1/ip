package blob.commands;

import blob.tasks.TaskList;

/**
 * The TaskCommand class represents a command that interacts with a task list
 * when executed
 */
public abstract class TaskCommand extends Command {
    /** The task list that the command will interact with */
    protected TaskList taskList;

    public TaskCommand(String commandWord) {
        super(commandWord);
    }

    /**
     * Sets the task list for the command to interact with
     *
     * @param taskList The task list to set.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}

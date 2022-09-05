package main.java.amanda.command;

import main.java.amanda.exception.AmandaException;
import main.java.amanda.manager.StoreManager;
import main.java.amanda.manager.TaskList;
import main.java.amanda.task.Task;
import main.java.amanda.ui.Ui;

/**
 * Command that represent a command inputted by user.
 */
public class Command {

    protected Task task;
    protected int taskNo;

    /**
     * Constructor of Command class.
     * @param task The task associated with this Command.
     * @param taskNo The index in the current task list of the task associated with this Command.
     */
    public Command(Task task, int taskNo) {
        this.task = task;
        this.taskNo = taskNo;
    }

    /**
     * Executes the command.
     * @param tasks the current task list.
     * @param ui the current Ui.
     * @param store the store manager that write any changes to the storage file.
     * @throws AmandaException to handle any wrong input by the user.
     */
    public void execute(TaskList tasks, Ui ui, StoreManager store) throws AmandaException {

    }

    /**
     * Checks if the current command is an Exit Command.
     * @return if the current command is an instance of ExitCommand.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}

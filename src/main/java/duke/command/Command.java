package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Abstract class for a command
 *
 * @author kaij77
 * @version 0.1
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Method which executes the command. Execution details is implemented by the specific commands.
     *
     * @param taskList A list of tasks
     * @param ui A ui responsible for printing output to the user
     */
    public abstract void execute(TaskList taskList, Ui ui);

    /**
     * Sets isExit of the command to true.
     */
    public void setExit() {
        this.isExit = true;
    }

    /**
     * Returns the value of the command's isExit.
     *
     * @return boolean isExit
     */
    public boolean getExit() {
        return this.isExit;
    }
}

/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * public abstract class Command that checks command.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * public class constructor for Command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * public method isExit to return whether command is bye and to terminate code.
     * @return
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * public method execute to execute command.
     * @param tasks
     * @param ui
     * @param storage
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}


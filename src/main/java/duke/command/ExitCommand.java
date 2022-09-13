package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command child class that is used to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * @param tasks   TaskList object corresponding to all tasks
     * @param ui      Ui object to show user output/errors
     * @param storage Storage object to save data after execution
     * @return Result of execution as a string to be shown to user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        return "";
    }
}

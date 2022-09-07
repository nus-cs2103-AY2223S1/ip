package command;

import myduke.Storage;
import myduke.TaskList;
import myduke.Ui;
import javafx.application.Platform;

public class ExitCommand extends Command {

    /**
     * Method that exits the task manager, and saves the tasks in the storage file.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return ui.goodbye();
    }
}

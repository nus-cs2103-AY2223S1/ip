package command;

import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;
import javafx.application.Platform;

/**
 * This class encapsulates a command asking Koba to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Exits from Koba program.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     * @return a String saying goodbye to user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return ui.goodbye();
    }
}

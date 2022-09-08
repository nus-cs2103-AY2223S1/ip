package iana.command;

import iana.exception.IanaException;
import iana.storage.Storage;
import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command that exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Runs the command to exit the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            Storage.store(tasks);
        } catch (IanaException e) {
            ui.say(e.getMessage());
        }
        return ui.sayBye();
    }

    /**
     * Returns true as command is exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
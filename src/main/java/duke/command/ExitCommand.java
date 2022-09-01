package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ExitCommand class represents a Command that exits the task manager.
 *
 * @author Edric Yeo
 */
public class ExitCommand extends Command {

    /**
     * Method that checks if the Command is an ExitCommand.
     *
     * @return True for all ExitCommands.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Method that exits the task manager, and saves the tasks in the storage file.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            return "Error saving file!!";
        }
        return ui.showBye();
    }

    /**
     * Method that returns the String representation of an ExitCommand.
     *
     * @return String representation of an ExitCommand.
     */
    @Override
    public String toString() {
        return "Exit command";
    }
}

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
     * Returns true if the Command is an ExitCommand.
     *
     * @return True for all ExitCommands.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns a message to indicate exits the task manager.
     * Writes the current tasks into the storage file.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     * @return A message to say goodbye to the user.
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
     * Returns the String representation of an ExitCommand.
     *
     * @return String representation of an ExitCommand.
     */
    @Override
    public String toString() {
        return "Exit command";
    }
}

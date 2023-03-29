package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command to show an invalid command to the user
 */
public class IncorrectCommand extends Command {

    private String message;

    /**
     * Constructs an IncorrectCommand instance
     *
     * @param message the message to be displayed to the user
     */
    public IncorrectCommand(String message) {
        super();
        this.message = message;
    }

    /**
     * Returns a boolean value true if the command is a bye command,
     * false otherwise.
     *
     * @return a boolean value on whether the command is a bye command
     */
    @Override
    public boolean isByeCommand() {
        return false;
    }

    /**
     * Executes the command to display the command message to the user
     *
     * @param tasks The current list of tasks
     * @param ui The Ui instance to return the result to the user
     * @param storage The Storage instance to store the result to local storage
     * @return the string representation of the execution result
     * @throws DukeException if errors are encountered during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = getResultString();
        ui.showErrorMessage(result);
        return result;
    }

    /**
     * Gets the string representation of the incorrect command message
     *
     * @return A string presentation of the incorrect command message
     */
    private String getResultString() {
        return "Woof! " + message;
    }
}

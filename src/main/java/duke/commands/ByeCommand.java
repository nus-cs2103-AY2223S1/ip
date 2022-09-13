package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command to end the current session
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Constructs a ByeCommand instance
     */
    public ByeCommand() {
        super();
    }

    /**
     * Returns a boolean value true if the command is a bye command,
     * false otherwise.
     *
     * @return a boolean value on whether the command is a bye command
     */
    @Override
    public boolean isByeCommand() {
        return true;
    }

    /**
     * Executes the command to store the current tasks to the local storage
     *
     * @param tasks The current list of tasks
     * @param ui The Ui instance to return the result to the user
     * @param storage The Storage instance to store the result to local storage
     * @return the string representation of the execution result
     * @throws DukeException if errors are encountered during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.writeToFile(tasks.toArrayList());
        ui.showMessage("Shiba hope you had a productive time today!\nDon't forget to come and work with me again \uD83D\uDC36");
        return "Shiba hope you had a productive time today!\nDon't forget to come and work with me again \uD83D\uDC36";
    }
}
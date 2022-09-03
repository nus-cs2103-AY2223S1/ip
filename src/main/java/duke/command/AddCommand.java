package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.StorageList;
import duke.Ui;

/**
 * Add a new Task to the StorageList.
 */
public class AddCommand extends Command {
    /**
     * Executes the add command.
     *
     * @param ui the user interface
     * @param storageList the storage list
     * @throws DukeException if the command is invalid
     */
    @Override
    public void execute(Ui ui, StorageList storageList) throws DukeException {
        String input = ui.getLastInput();
        String command = ui.getLastCommand();
        String secCommand;
        switch (command) {
        case "dl":
        case "deadline":
            secCommand = "/by";
            break;
        case "e":
        case "event":
            secCommand = "/at";
            break;
        case "t":
        case "todo":
            secCommand = "";
            break;
        default:
            throw new DukeException("Invalid command.");
        }
        Parser.parseTask(input, command, storageList, secCommand);
    }
}

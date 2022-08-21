package duke.command;

import duke.DukeException;
import duke.Output;
import duke.Parser;
import duke.StorageList;
import duke.Ui;

/**
 * Searches for the list of Tasks that matches the given keyword/regex.
 */
public class FindCommand extends Command {
    /**
     * Executes the find command.
     *
     * @param ui The user interface
     * @param storageList The storage list
     * @throws DukeException If the command is invalid
     */
    @Override
    public void execute(Ui ui, StorageList storageList) throws DukeException {
        String input = ui.getLastInput();
        String command = ui.getLastCommand();
        Output.FIND.listMatches(storageList, Parser.findFirstCommand(input, command));
    }
}

package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.StorageList;
import duke.Ui;

public class AddCommand extends Command {
    @Override
    public void execute(Ui ui, StorageList storageList) throws DukeException {
        String input = ui.getLastInput();
        String command = ui.getLastCommand();
        String secCommand;
        switch (command) {
        case "deadline":
            secCommand = "/by";
            break;
        case "event":
            secCommand = "/at";
            break;
        case "todo":
            secCommand = "";
            break;
        default:
            throw new DukeException("Invalid command.");
        }
        Parser.parseTask(input, command, storageList, secCommand);
    }
}

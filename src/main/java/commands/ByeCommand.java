package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

/**
 * Adds a person to the address book.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ByeCommand() {
        super();
    }

    @Override
    public boolean isByeCommand() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.writeToFile(tasks.toArrayList());
        ui.showMessage("Duke sad to see you leave :(");
    }
}
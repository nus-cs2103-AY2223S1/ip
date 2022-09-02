package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
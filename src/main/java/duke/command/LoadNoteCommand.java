package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to load note from the file
 *
 * @author Pontakorn Prasertsuk
 */
public class LoadNoteCommand extends Command {

    private String key;

    /**
     * Constructs a new LoadNoteCommand instance.
     *
     * @param key note name
     */
    public LoadNoteCommand(String key) {
        this.key = key;
    }

    /**
     * Executes the AddNoteCommand
     *
     * @param taskList not being used
     * @param ui the user interface to be used
     * @param storage the storage to be used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showOutput("Here is your note:");
        ui.showOutput(storage.loadNote(key));

        return "Here is your note:\n" + storage.loadNote(key);
    }

    /**
     * Returns false as this is not the exit command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

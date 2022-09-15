package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to delete note file
 *
 * @author Pontakorn Prasertsuk
 */
public class DeleteNoteCommand extends Command {

    private String key;

    /**
     * Constructs a new DeleteNoteCommand instance.
     *
     * @param key note name
     */
    public DeleteNoteCommand(String key) {
        this.key = key;
    }

    /**
     * Executes the DeleteNoteCommand
     *
     * @param taskList not being used
     * @param ui the user interface to be used
     * @param storage the storage to be used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.deleteNote(key);
        ui.showOutput("Note deleted successfully!");

        return "Note deleted successfully!";
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

package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to add new note
 *
 * @author Pontakorn Prasertsuk
 */
public class AddNoteCommand extends Command {

    private String key;
    private String note;

    /**
     * Constructs a new AddNoteCommand instance.
     *
     * @param key note name
     * @param note note content
     */
    public AddNoteCommand(String key, String note) {
        this.key = key;
        this.note = note;
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
        storage.saveNote(key, note);
        ui.showOutput("Note added.");

        return "Note added.";
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

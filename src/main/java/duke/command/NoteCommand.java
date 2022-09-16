package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Note;

public class NoteCommand extends Command {
    private final String[] STR;

    /**
     * Creates a new NoteCommand object.
     *
     * @param str The array String of the note containing the description.
     */
    public NoteCommand(String[] str) {
        this.STR = str;
    }

    /**
     * Executes the note command the user inputs.
     *
     * @param tasks The list in which the note is to be added.
     * @param ui The ui to deal with user interactions.
     * @param storage The storage to be updated with the newly added note.
     * @throws DukeException
     *          Thrown when the description is not given.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Note myNote = null;
        try {
            myNote = new Note(STR[1]);
        } catch (Exception e) {
            ui.emptyDescription();
        }
        tasks.add(myNote);
        storage.writeFile(tasks);
        return ui.add(myNote);
    }
}

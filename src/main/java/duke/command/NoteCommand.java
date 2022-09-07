package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.note.Note;
import duke.note.NoteList;

public class NoteCommand extends Command {
    private String description;

    /**
     * Creates a new TodoCommand.
     *
     * @param task
     */
    public NoteCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage storage) throws DukeException {
        notes.add(new Note(description));
        return ui.printAddNotesSuccessfully(notes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof NoteCommand == false) {
            return false;
        }
        NoteCommand that = (NoteCommand) o;
        return description.equals(that.description);
    }
}

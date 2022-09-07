package duke.command;

import duke.exception.DukeException;
import duke.task.Note;
import duke.task.TaskList;

public class AddNoteCommand extends Command {
    private String note;

    public AddNoteCommand(String note) {
        this.note = note;
    }

    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful command execution message
     * @throws DukeException if user input has wrong format
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        Note newNote = new Note(note);
        return taskList.addNote(newNote);
    }
}

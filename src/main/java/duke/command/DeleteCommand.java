package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.note.Note;
import duke.note.NoteList;
import duke.types.ListObject;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private ListObject objectType;
    private int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(ListObject objectType, int index) {
        super();
        this.objectType = objectType;
        this.index = index;
    }

    private String deleteTask(TaskList tasks) throws DukeException {
        if (index == -1 || index >= tasks.size()) {
            throw new DukeException(String.format("%s number %d not found! Unable to delete %s.",
                objectType.label.substring(0, 1).toUpperCase() + objectType.label.substring(1),
                index + 1, objectType.label));
        }
        Task task = tasks.remove(index);
        return String.format("Noted. I've removed this %s:\n%s\nNow you have %d %s in the list.",
            objectType.label, task, tasks.size(), objectType.label);
    }

    private String deleteNote(NoteList notes) throws DukeException {
        if (index == -1 || index >= notes.size()) {
            throw new DukeException(String.format("%s number %d not found! Unable to delete %s.",
                objectType.label.substring(0, 1).toUpperCase() + objectType.label.substring(1),
                index + 1, objectType.label));
        }
        Note note = notes.remove(index);
        return String.format("Noted. I've removed this %s:\n%s\nNow you have %d %s in the list.",
            objectType.label, note, notes.size(), objectType.label);
    }

    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage storage) throws DukeException {
        if (objectType == ListObject.TASK) {
            return deleteTask(tasks);
        } else {
            return deleteNote(notes);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof DeleteCommand == false) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return index == that.index;
    }
}

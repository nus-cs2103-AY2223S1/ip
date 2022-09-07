package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.note.Note;
import duke.note.NoteList;

/**
 * Represents a command to search through tasks or notes.
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Creates a new FindCommand.
     *
     * @param toFind the string to search for in objects' descriptions within tasks or notes.
     */
    public FindCommand(String toFind) {
        super();
        this.toFind = toFind;
    }

    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.find(toFind);
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        if (foundTasks.size() == 0) {
            listString = new StringBuilder(String.format("No tasks found containing \"%s\".", toFind));
        } else {
            for (int i = 0; i < foundTasks.size(); i++) {
                listString.append(String.format("%d.%s\n", i + 1, foundTasks.get(i).toString()));
            }
        }

        listString.append("\n");
        ArrayList<Note> foundNotes = notes.find(toFind);
        if (foundNotes.size() == 0) {
            listString.append(String.format("No notes found containing \"%s\".", toFind));
        } else {
            listString.append("Here are the notes in your list:\n");
            for (int i = 0; i < foundNotes.size(); i++) {
                listString.append(String.format("%d.%s\n", i + 1, foundNotes.get(i).toString()));
            }
        }
        return listString.toString();
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.note.NoteList;

/**
 * Represents a command to list all tasks in the tasklist.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage storage) {
        // Build the list string from the tasks
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listString.append(String.format("%d.", i + 1));
            listString.append(tasks.get(i).toString());
            listString.append("\n");
        }

        // Append notes into list string
        listString.append("\nHere are your notes:\n");
        for (int i = 0; i < notes.size(); i++) {
            listString.append(String.format("N%d.", i + 1));
            listString.append(notes.get(i).toString());
            listString.append("\n");
        }

        if (listString.length() > 0) {
            listString.setLength(listString.length() - 1);
        }
        return listString.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}

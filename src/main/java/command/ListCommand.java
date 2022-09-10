package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import task.NotesList;
import ui.Ui;
import exception.DukeException;

/**
 * Represents a ListCommand that inherits from
 * the abstract class Command.
 */
public class ListCommand extends Command {
    protected String commandLine;

    /**
     * Constructor for this ListCommand that takes in a
     * commandLine as a String.
     *
     * @param commandLine Command from the user.
     */
    public ListCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    /**
     * Returns a boolean false to show that this
     * is not the last command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out all the tasks from the taskList if successful.
     *
     * @param taskList The ArrayList that contains all the tasks.
     * @param ui The Ui that interacts with the next command from the user.
     * @param storage The Storage used to store the tasks.
     * @throws DukeException If the taskList is empty.
     */
    @Override
    public String execute(TaskList taskList, NotesList notesList, Ui ui, Storage storage) {
        String response = "";
        try {
            assert !taskList.isEmpty();
            assert !notesList.isEmpty();
            if (taskList.size() == 0) {
                throw new DukeException("There are no tasks in your list. :)");
            } else {
                System.out.println("Here are the tasks in your list:");
                taskList.forEach();
                System.out.println();
                System.out.println("Here are the notes in your list:");
                notesList.forEach();
                System.out.println();
                response = "Here are the tasks in your list:\n";
                for (int i = 0; i < taskList.size(); i++) {
                    Task currTask = taskList.get(i);
                    String currTaskString = currTask.toString();
                    response += currTaskString + "\n";
                }

                for (int j = 0; j < notesList.size(); j++) {
                    Task currNote = notesList.get(j);
                    String currNoteString = currNote.toString();
                    response += currNoteString + "\n";
                }
                return response;
            }
        } catch (DukeException e) {
            System.out.println(e.toString());
            return e.toString();
        }
    }
}

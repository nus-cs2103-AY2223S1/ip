package command;

import storage.Storage;
import task.NotesList;
import task.TaskList;
import ui.Ui;
import exception.DukeException;
import task.Task;
import task.Notes;

/**
 * Represents a Delete Command that inherits from
 * the abstract class Command.
 */
public class DeleteCommand extends Command {
    protected String commandLine;

    /**
     * Constructor for this DeleteCommand that takes in a
     * commandLine as a String.
     *
     * @param commandLine Command from the user.
     */
    public DeleteCommand(String commandLine) {
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
     * Executes the command to delete a task that the user
     * wants to remove.
     *
     * @param taskList The ArrayList that contains all the tasks.
     * @param ui The Ui that interacts with the next command from the user.
     * @param storage The Storage used to store the tasks.
     * @throws DukeException If there are no such task in the taskList.
     */
    @Override
    public String execute(TaskList taskList, NotesList notesList, Ui ui, Storage storage) {
        String response = "";
        int index = Integer.parseInt(commandLine.substring(7)) - 1;
        try {
            assert !taskList.isEmpty();
            assert !notesList.isEmpty();
            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException("You have no such tasks.");
            } else {
                Task task = taskList.get(index);
                if (task instanceof Notes) {
                    notesList.remove(index);
                    response = "Noted. I've removed this task:\n"
                            + " " + task.toString() + "\n" + "Now you have "
                            + notesList.size() + " tasks in the list.\n";
                } else {
                    taskList.remove(index);
                    response = "Noted. I've removed this note:\n"
                            + " " + task.toString() + "\n" + "Now you have "
                            + taskList.size() + " notes in the list.\n";
                }
                storage.updateData(taskList, notesList);
                System.out.println(response);
                return response;
            }
        } catch (DukeException e) {
            System.out.println(e.toString());
            return e.toString();
        }
    }
}

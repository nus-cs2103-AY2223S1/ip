package command;

import storage.Storage;
import task.TaskList;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskList.size() == 0) {
                throw new DukeException("There are no tasks in your list. :)");
            } else {
                System.out.println("Here are the tasks in your list:");
                taskList.forEach();
                System.out.println();
            }
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
}

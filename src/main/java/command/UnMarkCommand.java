package command;

import exception.KobaException;
import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;

/**
 * This class encapsulates a command asking Koba to unmark a task.
 */
public class UnMarkCommand extends Command {
    private int index;

    /**
     * Constructs a UnMarkCommand, given a Task.
     *
     * @param index The index of the Task in the TaskList to be marked
     *              as done.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the Task in the TaskList and returns a confirmation message.
     *
     * @param tasklist The TaskList instance for the task manager.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     * @return a String confirming that the task has been unmarked.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.unMarkTask(index);
            storage.saveToFile(tasklist);
            return ui.unMarkCommandtoString(tasklist.getTask(index));
        } catch (KobaException e) {
            return e.getMessage();
        }
    }
}

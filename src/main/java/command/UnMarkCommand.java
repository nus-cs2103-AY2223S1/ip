package command;

import exception.DukeException;
import myduke.Storage;
import myduke.TaskList;
import myduke.Ui;

public class UnMarkCommand extends Command {
    private int index;

    /**
     * Constructor for a UnMarkCommand instance, given a Task.
     *
     * @param index The index of the Task in the TaskList to be marked
     *              as done.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Method that marks the Task in the TaskList as not done.
     *
     * @param tasklist The TaskList instance for the task manager.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.unMarkTask(index);
            storage.saveToFile(tasklist);
            return ui.unMarkCommandtoString(tasklist.getTask(index));
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

package command;

import exception.DukeException;
import myduke.Storage;
import myduke.TaskList;
import myduke.Ui;

public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for a MarkCommand instance, given a Task.
     *
     * @param index The index of the Task in the TaskList to be marked
     *              as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Method that marks the Task in the TaskList as done.
     *
     * @param tasklist The TaskList instance for the task manager.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.markTask(index);
            storage.saveToFile(tasklist);
            return ui.markCommandtoString(tasklist.getTask(index));
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

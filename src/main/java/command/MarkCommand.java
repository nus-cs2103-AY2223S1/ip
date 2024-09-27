package command;

import exception.KobaException;
import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;

/**
 * This class encapsulates a command asking Koba to mark a task as completed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand, given a Task.
     *
     * @param index The index of the Task in the TaskList to be marked
     *              as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the Task in the TaskList as done and returns a confirmation message
     *
     * @param tasklist The TaskList instance for the task manager.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     * @return a String confirming that the task has been marked.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.markTask(index);
            storage.saveToFile(tasklist);
            return ui.markCommandtoString(tasklist.getTask(index));
        } catch (KobaException e) {
            return e.getMessage();
        }
    }
}

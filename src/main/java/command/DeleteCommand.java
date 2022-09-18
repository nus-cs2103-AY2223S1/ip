package command;

import exception.KobaException;
import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;
import task.Task;

/**
 * This class encapsulates a command asking Koba to delete a task from tasklist.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand, given a Task to be deleted.
     *
     * @param index The index of Task to be deleted from the TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the Task from the TaskList and returns a confirmation message.
     *
     * @param tasklist The TaskList to delete the task from.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     * @return a String confirming that the command has been executed.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            Task deleted = tasklist.deleteTask(index);
            storage.saveToFile(tasklist);
            return ui.deleteTasktoString(deleted, tasklist);
        } catch (KobaException e) {
            return e.getMessage();
        }
    }
}

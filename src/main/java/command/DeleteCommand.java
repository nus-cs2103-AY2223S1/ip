package command;

import exception.DukeException;
import myduke.Storage;
import myduke.TaskList;
import myduke.Ui;
import task.Task;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for a DeleteCommand instance, given a Task to be deleted.
     *
     * @param index The index of Task to be deleted from the TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Method that deletes the Task from the TaskList.
     *
     * @param tasklist The TaskList instance for the task manager.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            Task deleted = tasklist.deleteTask(index);
            storage.saveToFile(tasklist);
            return ui.deleteTasktoString(deleted, tasklist);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

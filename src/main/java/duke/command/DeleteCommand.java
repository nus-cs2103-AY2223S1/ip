package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * DeleteCommand is the command for when a task is removed from the list.
 */
public class DeleteCommand extends Command {
    private int num;

    /**
     * Constructor for DeleteCommand.
     *
     * @param num Number of the task to be deleted.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     * @return String that matches the command input.
     */
    @Override
    public String execCommand(TaskList list, Ui ui, Storage storage) {
        Task removed = list.removeTask(this.num);
        storage.saveList(list.save());
        return ui.showDelete(removed, list.getSize());
    }
}

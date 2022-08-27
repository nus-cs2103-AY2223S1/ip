package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to delete a task from task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for DeleteCommand class.
     *
     * @param index index of task to delete in task list.
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Deletes task from task list and saves changes made to task list.
     *
     * @param taskList task list.
     * @param ui user interface of program.
     * @param storage files storing task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deletedTask = taskList.get(index);
        taskList.delete(index);
        ui.showDelete(taskList, deletedTask);
        new SaveCommand().execute(taskList, ui, storage);
    }
}

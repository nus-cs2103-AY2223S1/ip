package zeus.command;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;
import zeus.task.Task;

/**
 * Handles deleting a Task.
 */
public class DeleteCommand extends Command {

    private int idx;

    /**
     * Constructor of DeleteCommand class.
     *
     * @param idx Index of Task to be deleted in task list.
     */

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes deletion of Task from task list.
     *
     * @param taskList List of tasks.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws ZeusException If input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        if (this.idx < 0 || this.idx >= taskList.getSize()) {
            throw new ZeusException("☹ OOPS!!! Invalid index entered");
        }
        taskList.saveCurrentTaskListVersion();

        Task task = taskList.getTask(this.idx);
        taskList.removeTask(this.idx);
        ui.printDeleteTask(task, taskList.getSize());
        storage.saveTasksToDisk(taskList.getTaskList());
    }
}

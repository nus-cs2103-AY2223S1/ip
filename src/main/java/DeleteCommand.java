/**
 * Command that handles deleting a Task.
 */
public class DeleteCommand extends Command {

    private int idx;

    /**
     * Constructor of DeleteCommand class.
     *
     * @param idx Index of Task to be deleted in task list
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes deletion of Task from task list.
     *
     * @param taskList List of tasks
     * @param ui The Ui
     * @param storage The Storage
     * @throws ZeusException If input is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        if (this.idx < 0 || this.idx >= taskList.size()) {
            throw new ZeusException("☹ OOPS!!! Invalid index entered");
        }
        Task t = taskList.getTask(this.idx);
        taskList.removeTask(this.idx);
        ui.printDeleteTask(t, taskList.size());
        storage.saveTasksToDisk(taskList.getTaskList());
    }
}

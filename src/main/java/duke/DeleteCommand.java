package duke;

public class DeleteCommand extends Command {
    private int idx;

    /**
     * Constructor for DeleteCommand
     *
     * @param idx index of task in taskList to be deleted
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Deletes task from taskList and save changes
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     * @return String to be displayed to user after execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String s = taskList.deleteTask(idx);
        storage.save(taskList);
        return s;
    }
}

/**
 * This class encapsulates a delete command from the user.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private TaskList taskList;
    private int pos;

    DeleteCommand(TaskList taskList, int pos) throws DwukeException {
        if (pos < 0 || pos > taskList.size() - 1) {
            throw new DwukeException("oops!!! da wist doesn't have a task with da number :3");
        }
        this.taskList = taskList;
        this.pos = pos;
    }

    /**
     * Deletes the task at the position of the task list.
     *
     * @return A String signalling that the task has been deleted.
     */
    @Override
    public String execute() {
        Task task = this.taskList.remove(this.pos);
        return "me've wemoved dis task:\n" + task
                + "\nnow you have " + this.taskList.size() + " tasks in da wist uWu";
    }
}

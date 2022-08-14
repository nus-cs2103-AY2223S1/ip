/**
 * This class encapsulates an unmark command from the user.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private TaskList taskList;
    private int pos;

    UnmarkCommand(TaskList taskList, int pos) throws DwukeException {
        if (pos < 0 || pos > taskList.size() - 1) {
            throw new DwukeException("oops!!! da wist doesn't have a task with da number :3");
        }
        this.taskList = taskList;
        this.pos = pos;

    }

    /**
     * Marks the task at the position of the task list as not done.
     *
     * @return A String signalling that the task has been marked as not done.
     */
    @Override
    public String execute() {
        Task task = this.taskList.unmark(this.pos);
        return "okies, me've mawked dis task as not done yet:\n" + task;
    }
}

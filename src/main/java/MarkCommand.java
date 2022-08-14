/**
 * This class encapsulates a mark command from the user.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private TaskList taskList;
    private int pos;

    MarkCommand(TaskList taskList, int pos) throws DwukeException {
        if (pos < 0 || pos > taskList.size() - 1) {
            throw new DwukeException("oops!!! da wist doesn't have a task with da number :3");
        }
        this.taskList = taskList;
        this.pos = pos;

    }

    /**
     * Marks the task at the position of the given task list as done.
     *
     * @return A String signalling that the task has been marked as done.
     */
    @Override
    public String execute() {
        Task task = this.taskList.mark(this.pos);
        return "nwice! me've mawked dis task as done:\n" + task;
    }
}

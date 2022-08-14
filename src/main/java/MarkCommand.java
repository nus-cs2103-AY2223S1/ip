/**
 * This class encapsulates a mark command from the user.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int pos;

    MarkCommand(int pos) {
        this.pos = pos;
    }

    /**
     * Marks the task at the position of the given task list as done.
     *
     * @param taskList The task list to mark.
     * @return A String signalling that the task has been marked as done.
     */
    @Override
    public String execute(TaskList taskList) {
        Task task = taskList.mark(this.pos);
        return "Well done! I've marked this task as done:\n" + task;
    }
}

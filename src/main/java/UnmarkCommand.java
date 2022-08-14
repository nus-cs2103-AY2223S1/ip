/**
 * This class encapsulates an unmark command from the user.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int pos;

    UnmarkCommand(int pos) {
        this.pos = pos;
    }

    /**
     * Marks the task at the position of the given task list as not done.
     *
     * @param taskList The task list to mark.
     * @return A String signalling that the task has been marked as not done.
     */
    @Override
    public String execute(TaskList taskList) {
        Task task = taskList.unmark(this.pos);
        return "OK, I've marked this task as not done:\n" + task;
    }
}

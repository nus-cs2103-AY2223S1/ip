/**
 * Represents an input command to the Duke application.
 *
 * @author WR3nd3
 */
abstract public class Command {

    /**
     * Executes relevant procedures to this command to the task list, UI, and list loader.
     *
     * @param tasks TaskList containing the tasks on the list.
     * @param ui Ui that interacts with the input of users and output from Duke.
     * @param storage ListLoader that updates TaskList information on a separate file.
     */
    abstract public void execute(TaskList tasks, Ui ui, ListLoader storage);

    /**
     * Returns whether the command is an exit command.
     *
     * @return Boolean on whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}

/**
 * Class representing the command when user inputs list command.
 *
 * @author AkkFiros
 */

public class ListCommand extends Command {
    public static final String KEYWORD = "list";

    /**
     * Returns a command for KKBot when user inputs "list".
     * @param tasks the list of tasks stored by KKBot
     * @param display the display object that governs what response is returned to the user
     * @return the message with the list of all tasks in KKBot
     */
    @Override
    public String execute(TaskList tasks, Display display) throws InvalidTaskException{
        return display.showAllTasks(tasks);
    }
}

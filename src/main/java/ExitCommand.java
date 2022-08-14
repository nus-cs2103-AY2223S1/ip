/**
 * This class encapsulates an exit command from the user.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    ExitCommand() {
    }

    /**
     * Returns an empty String.
     *
     * @param taskList The task list to ignore.
     * @return An empty String.
     */
    @Override
    public String execute(TaskList taskList) {
        return "";
    }
}

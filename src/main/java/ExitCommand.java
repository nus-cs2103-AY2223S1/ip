/**
 * This class encapsulates an exit command from the user.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Returns an empty String.
     *
     * @return An empty String.
     */
    @Override
    public String execute() {
        return "";
    }
}

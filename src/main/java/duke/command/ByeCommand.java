package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a ByeCommand object to be called when user inputs 'bye'.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String DUKE_END =
            "I'm glad I could help, my brilliant scientist! All the best in your research!";

    /**
     * Return message when bye command executes.
     *
     * @param tasks
     * @param storage
     * @return bye command message
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return DUKE_END;
    }
}

package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a ByeCommand object to be called when user inputs 'bye'.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String DUKE_END =
            "Pleasure to be at your service! Run me again if you need more assistance! :)";

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

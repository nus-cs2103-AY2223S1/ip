package duke.command;

import duke.FileStorage;
import duke.task.TaskList;

/**
 * Command used to exit the session with the chatBot.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    /**
     * Ends the session with the user, returning
     *     the corresponding message to the GUI.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     */
    @Override
    public String execute(TaskList list, FileStorage storage) {
        return "Farewell, till we meet again";
    }
}

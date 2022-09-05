package dobby.commands;

import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.UserInput;

/**
 * Class that returns error if exists.
 */
public class ErrorCommand extends Command {
    /**
     * Returns error message if needed.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        DobbyChat.unknown();
    }
}

package dobby.commands;

import java.io.IOException;

import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.UserInput;


/**
 * Class that lists out tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) throws IOException {
        if (dl.isEmpty()) {
            DobbyChat.listEmpty();
        } else {
            DobbyChat.list(dl);
        }
    }
}

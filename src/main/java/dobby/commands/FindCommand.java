package dobby.commands;

import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.UserInput;

import java.io.IOException;

/**
 * Class that finds tasks in the list.
 */
public class FindCommand extends Command {
    /**
     * Executes the find command.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) throws IOException {
        if (dl.isEmpty()) {
            DobbyChat.listEmpty();
        } else {
            DobbyChat.echo(dl.toFind(ui.getDesc()));
        }
    }
}

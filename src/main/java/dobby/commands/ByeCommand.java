package dobby.commands;

import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.UserInput;
import dobby.DobbyIO;

import java.io.IOException;

/**
 * Class that saves the list and marks the bye flag.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     * @throws IOException
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) throws IOException {
        isBye();
        DobbyIO.save(dl);
        DobbyChat.sayBye();
        isBye = true;
    }
}

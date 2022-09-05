package dobby.commands;

import java.io.IOException;

import dobby.Dobby;
import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.DobbyStorage;
import dobby.UserInput;


/**
 * Class that saves the list and marks the bye flag.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     * @throws IOException when IOException is present
     */
    @Override
    public void execute(DobbyList dl, UserInput ui) throws IOException {
        isBye();
        DobbyStorage.save(dl, Dobby.getFilePath());
        DobbyChat.getBye();
        isBye = true;
    }
}

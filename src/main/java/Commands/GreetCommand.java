package Commands;

import DataStruct.TaskList;
import DaveExceptions.DaveException;
import Storage.ArchiveHandler;

import java.util.Objects;

public class GreetCommand extends Command{
    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() {
        return "Tohno Mansion's loyal maid, Kohaku-chan, at your service! How can I help you today, master?";
    }
}

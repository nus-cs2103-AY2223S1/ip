package sky.command;

import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.storage.History;
import sky.storage.Storage;

/**
 * The InvalidCommand class deals with invalid inputs.
 */
public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, History history) throws TextNoMeaningException {
        throw new TextNoMeaningException("Are you new? Type a command that I actually know.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

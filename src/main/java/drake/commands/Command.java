package drake.commands;

import java.io.IOException;
import java.util.List;

import drake.DrakeException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;

/**
 * Represents a command given by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The task list before the command is executed.
     * @param ui      Gives access to the UI of the program.
     * @param storage Gives access to local storage.
     * @return The list of replies
     * @throws IOException    when there is an issue with the IO.
     * @throws DrakeException when there is inappropriate input or save file issues.
     */
    public abstract List<String> execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException;

    /**
     * Checks whether the user has given the bye command.
     *
     * @return True if the user has given the bye command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

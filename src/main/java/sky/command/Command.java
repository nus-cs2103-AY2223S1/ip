package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.exception.TextNoMeaningException;

import java.io.IOException;

/**
 * The abstract Command class deals with executing tasks involving TaskList, Ui, and Storage.
 */
public abstract class Command {
    /**
     * Performs from a set of actions, which includes adding/deleting tasks from the task list,
     * printing the task list contents, ending the program etc.
     *
     * @param taskList Task list that contains all the tasks.
     * @param storage Storage representing the task file in the user's hard disk.
     * @return Output of the command in the form of a String.
     * @throws TextNoMeaningException If the command is an invalid one.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws TextNoMeaningException, IOException;

    /**
     * Returns a boolean indicating if the command is an exit one.
     *
     * @return A boolean indicating if the command is an exit one.
     */
    public abstract boolean isExit();
}

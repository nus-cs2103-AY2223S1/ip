package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;
import exceptions.TumuException;
import tasks.Task;

/**
 * Class to be executed when a sort command is issued
 * by the user.
 */
public class SortCommand extends Command {
    private static final String FEEDBACK_MESSAGE = "Ok, I have "
            + "sorted the tasks for you in chronological order.";

    /**
     * Executes the command and gives the appropriate
     * feedback to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     * @throws TumuException Parent exception for the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TumuException {
        tasks.sortTasks();
        saveUserTasks(storage, tasks, ui);
        return FEEDBACK_MESSAGE;
    }
}

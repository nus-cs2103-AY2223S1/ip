package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Reperesents the commands inputted by users.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param taskName Name of task.
     * @param listOfTasks List where tasks are stored.
     * @param ui Ui object that is used to interact with users.
     * @param storage Storage to store the file of the list of tasks.
     * @throws IOException
     * @throws DukeEventEmptyException
     * @throws DukeDeadlineEmptyException
     * @throws DukeTodoEmptyException
     */
    abstract void execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage)
            throws IOException, DukeEventEmptyException, DukeDeadlineEmptyException, DukeTodoEmptyException;
}

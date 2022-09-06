package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * ListCommand is the Command when the user wants the list of the user's current tasks.
 */
public class ListCommand extends Command {
    private static final String NO_TASKS = "OOPS!!! You have no tasks in the list.";
    private static final String HEADER_MSG = "Here are the tasks in your list:"
            + System.lineSeparator();
    private static final String SEPARATOR = ".";
    /**
     * Displays the user's tasks.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            throw new DukeException(NO_TASKS);
        }
        String message = HEADER_MSG;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            message += (i + 1) + SEPARATOR + task + System.lineSeparator();
        }
        return message;
    }
}

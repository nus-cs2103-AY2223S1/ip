package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the command to mark a Task as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskIndex;

    /**
     * Constructor for a UnmarkCommand.
     * @param taskNum The task number of the task to be marked as not done.
     */
    public UnmarkCommand(int taskNum) {
        super();
        taskIndex = taskNum - 1;
    }

    /**
     * Marks a task as not done.
     * @param taskList List of tasks.
     * @param ui Shows the Task marked as not done.
     * @param storage Saves the modified list of tasks.
     * @return The message indicating that the Task has been marked as not done.
     * @throws DukeException If an invalid task number is provided by the user or if there
     *         is an error saving the modified list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.changeTaskStatus(taskIndex, false);
        storage.save(taskList);
        return ui.showTaskNotDone(unmarkedTask);
    }
}

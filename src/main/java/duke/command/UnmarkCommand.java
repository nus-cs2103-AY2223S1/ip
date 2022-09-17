package duke.command;

import duke.exception.FileIoException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of unmark a specific task in the task list.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND = "UNMARK";

    public static final String MESSAGE_USAGE = COMMAND
            + "\nUnmark a task, unmark <index>"
            + "\nExample: unmark 1";

    private static final int OFFSET = -1;
    private final int indexOfTaskToUnmark;


    /**
     * Constructs a UnmarkCommand instance.
     *
     * @param indexOfTaskToUnmark Indicates the index of the task that needs to be unmarked
     */
    public UnmarkCommand(int indexOfTaskToUnmark) {
        this.indexOfTaskToUnmark = indexOfTaskToUnmark + OFFSET;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws InvalidIndexException, FileIoException {

        assertIsValidIndex(tasks);

        Task taskToMark = getTask(tasks);
        storage.save(tasks);
        ui.showTask(taskToMark);
    }

    private Task getTask(TaskList tasks) {
        return tasks.unmarkTask(indexOfTaskToUnmark);
    }

    private void assertIsValidIndex(TaskList tasks) throws InvalidIndexException {
        if (indexOfTaskToUnmark < 0 || indexOfTaskToUnmark > tasks.getNumOfRemainingTasks()) {
            throw new InvalidIndexException();
        }
    }

}

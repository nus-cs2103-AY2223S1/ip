package duke.command;

import duke.exception.FileIoException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of marks specific task as done in the task list.
 */
public class MarkCommand extends Command {

    public static final String COMMAND = "MARK";

    public static final String MESSAGE_USAGE = COMMAND
            + "\nMarks a task, mark <index>"
            + "\nExample: mark 1";
    private static final int OFFSET = -1;
    private final int indexOfTaskToMark;


    /**
     * Constructs a MarkCommand instance.
     *
     * @param indexOfTaskToMark Indicates the index of the task that needs to be marked
     */
    public MarkCommand(int indexOfTaskToMark) {
        this.indexOfTaskToMark = indexOfTaskToMark + OFFSET;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws FileIoException {

        if (!isValidIndex(tasks)) {
            ui.showIndexOutOfBound(tasks.getNumOfRemainingTasks());
            return;
        }

        Task taskToMark = getTask(tasks);
        storage.save(tasks);
        ui.showTask(taskToMark);
    }

    private Task getTask(TaskList tasks) {
        return tasks.markTask(indexOfTaskToMark);
    }

    private boolean isValidIndex(TaskList tasks) {
        if (indexOfTaskToMark < 0 || indexOfTaskToMark >= tasks.getNumOfRemainingTasks()) {
            return false;
        }
        return true;
    }

}

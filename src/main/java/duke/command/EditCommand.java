package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An editCommand that encapsulates the operation of editing a specified task;
 */
public class EditCommand extends Command {

    public static final String COMMAND = "EDIT";

    public static final String MESSAGE_USAGE = COMMAND
            + "\nEdit a task, edit <index> <new task>"
            + "\nExample: edit 1 todo wash clothes";
    private static final int OFFSET = -1;
    private final int indexOfTaskToEdit;

    private final Task editedTask;

    /**
     * Creates a EditCommand with index of the task specified.
     *
     * @param indexOfTaskToEdit index
     * @param editedTask task
     */
    public EditCommand(int indexOfTaskToEdit, Task editedTask) {
        this.indexOfTaskToEdit = indexOfTaskToEdit + OFFSET;
        this.editedTask = editedTask;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {

        if (!isValidIndex(tasks)) {
            ui.showIndexOutOfBound(tasks.getNumOfRemainingTasks());
            return;
        }

        Task taskNeedsEdit = tasks.getTasks().get(indexOfTaskToEdit);
        tasks.edit(indexOfTaskToEdit, editedTask);
        storage.save(tasks);
        ui.showEditedMessage(taskNeedsEdit, editedTask);
    }

    private boolean isValidIndex(TaskList tasks) {
        if (indexOfTaskToEdit < 0 || indexOfTaskToEdit >= tasks.getNumOfRemainingTasks()) {
            return false;
        }
        return true;
    }
}

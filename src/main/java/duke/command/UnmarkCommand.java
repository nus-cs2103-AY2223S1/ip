package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    int taskNumber;

    /**
     * Initialises the UnmarkCommand with the task number of task to be unmarked.
     * @param taskNumber Task number of task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this command to mark a task as not done and update storage.
     * @param tasks Task list that task at task number is retrieved from.
     * @param ui UI to display success on unmarking.
     * @param storage Storage to be updated after unmarking task.
     * @throws DukeException If unable to write to storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNumber);
        task.markAsNotDone();
        storage.update(tasks);
        ui.showUnmarkedMessage(task);
    };

    /**
     * Checks if Duke application should exit after this command.
     * @return False as this command is not bye.
     */
    @Override
    public boolean isExit() {
        return false;
    };

}

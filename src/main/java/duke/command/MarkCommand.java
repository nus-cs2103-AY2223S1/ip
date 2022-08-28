package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    int taskNumber;

    /**
     * Initialises the MarkCommand with the task number of task to be marked.
     * @param taskNumber Task number of task to be marked.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this command to mark a task as done and update storage.
     * @param tasks Task list that task at task number is retrieved from.
     * @param ui UI to display success on marking.
     * @param storage Storage to be updated after marking task.
     * @throws DukeException If unable to write to storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNumber);
        task.markAsDone();
        storage.update(tasks);
        ui.showMarkedMessage(task);
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

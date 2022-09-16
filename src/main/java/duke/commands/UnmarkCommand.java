package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * This class represents a command to mark a task as not completed
 */
public class UnmarkCommand extends Command {

    private int taskNumber;

    /**
     * Constructs a new Unmark Command
     * @param taskNumber The task to mark as not completed
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task taskToUnmark = taskList.getTask(taskNumber - 1);
        taskToUnmark.markAsNotDone();
        storage.save(taskList);
        return ui.printUnmarkTask(taskToUnmark);
    }
}

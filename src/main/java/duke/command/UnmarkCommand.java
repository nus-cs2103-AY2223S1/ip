package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

/**
 * The UnmarkCommand is a command to unmarks the task to undone.
 *
 * @author Eugene Tan
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;

    /**
     * Constructor of UnmarkCommand.
     *
     * @param indexToUnmark The index to unmark task.
     */
    public UnmarkCommand(int indexToUnmark) {
        super();
        this.indexToUnmark = indexToUnmark;
    }

    /**
     * Marks the task as undone and prints it. Saves this update as well.
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     * @return Unmark message of task
     * @throws InvalidInputException if index is invalid
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (indexToUnmark > tasks.getSize() || indexToUnmark < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        storage.save(tasks.getTaskListInString());
        return ui.printUnmark(tasks.unMarkTask(this.indexToUnmark));
    }
}

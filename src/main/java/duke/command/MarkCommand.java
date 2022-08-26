package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

/**
 * The MarkCommand is a command to mark the task as done.
 *
 * @author Eugene Tan
 */
public class MarkCommand extends Command {
    int indexToMark;

    /**
     * Constructor of MarkCommand
     * @param indexToMark The index of the command to mark in the TaskList
     */
    public MarkCommand(int indexToMark) {
        super();
        this.indexToMark = indexToMark;
    }

    /**
     * Marks the task as done and prints it. Saves this update as well.
     *
     * @param tasks Tasklist containing the tasks.
     * @param ui Ui handling the user interaction.
     * @param storage Storage to store data.
     * @throws InvalidInputException if index entered is invalid.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (indexToMark > tasks.getSize() || indexToMark < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        ui.printMark(tasks.markTask(this.indexToMark));
        storage.save(tasks.getTaskListInString());
    }
}

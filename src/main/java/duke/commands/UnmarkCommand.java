package duke.commands;

import java.io.IOException;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Task;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidIntegerException;
import duke.exception.InvalidMarkException;

/**
 * The UnmarkCommand class encapsulates the execution of an unmark command.
 */
public class UnmarkCommand extends Command{
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the unmark command.
     *
     * @param taskList List where a specified task is to be marked as undone.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.input.matches("\\d+")) {
            int unMarkIndex = Integer.parseInt(this.input) - 1;
            if (unMarkIndex < 0 || unMarkIndex >= taskList.length()) {
                throw new InvalidIndexException(unMarkIndex + 1);
            } else {
                Task unMarkTask = taskList.index(unMarkIndex);
                if (!unMarkTask.getIsDone()) {
                    throw new InvalidMarkException(false, unMarkTask.toString());
                } else {
                    unMarkTask.markUndone();
                    storage.saveTasks(taskList);
                    return ui.print(false, unMarkTask);
                }
            }
        } else {
            throw new InvalidIntegerException(this.input);
        }
    }
}

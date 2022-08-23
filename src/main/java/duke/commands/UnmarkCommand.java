package duke.commands;

import java.io.IOException;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.tasks.*;

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
     * @param taskList List where a specified task is to be marked as undone.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.input.matches("\\d+")) {
            int unMarkIndex = Integer.parseInt(this.input) - 1;
            if (unMarkIndex < 0 || unMarkIndex >= taskList.length()) {
                throw new DukeException(String.format("There is no task with index %d", unMarkIndex + 1));
            } else {
                Task unMarkTask = taskList.index(unMarkIndex);
                if (!unMarkTask.getIsDone()) {
                    throw new DukeException("This task is already marked as undone:\n" + unMarkTask.toString());
                } else {
                    unMarkTask.markUndone();
                    ui.print("OK, I've marked this task as not done yet:\n" + unMarkTask.toString());
                }
            }
        } else {
            throw new DukeException(this.input + " is not an integer.");
        }
        storage.saveTasks(taskList);
    }
}

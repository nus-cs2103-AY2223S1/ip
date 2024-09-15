package duke.commands;

import java.io.IOException;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Task;
import duke.exception.DukeException;
import duke.exception.InvalidIntegerException;
import duke.exception.InvalidIndexException;

/**
 * The DeleteCommand class encapsulates the execution of a delete command.
 */
public class DeleteCommand extends Command{
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the delete command.
     *
     * @param taskList List where a specified task is to be deleted from it.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.input.matches("\\d+")) {
            int deleteIndex = Integer.parseInt(this.input) - 1;
            if (deleteIndex < 0 || deleteIndex >= taskList.length()) {
                throw new InvalidIndexException(deleteIndex + 1);
            } else {
                Task deletedTask = taskList.index(deleteIndex);
                taskList.remove(deleteIndex);
                String deleteMessage = "Noted. I've removed this task:\n" + deletedTask.toString() + "\n";
                storage.saveTasks(taskList);
                return ui.print(deleteMessage, taskList);
            }
        } else {
            throw new InvalidIntegerException(this.input);
        }
    }
}

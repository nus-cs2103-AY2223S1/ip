package duke.commands;

import java.io.IOException;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.tasks.*;

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
     * @param taskList List where a specified task is to be deleted from it.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.input.matches("\\d+")) {
            int deleteIndex = Integer.parseInt(this.input) - 1;
            if (deleteIndex < 0 || deleteIndex >= taskList.length()) {
                throw new DukeException(String.format("There is no task with index %d", deleteIndex + 1));
            } else {
                Task deletedTask = taskList.index(deleteIndex);
                taskList.remove(deleteIndex);
                String deleteMessage = "Noted. I've removed this task:\n" + deletedTask.toString() + "\n";
                ui.print(deleteMessage, taskList);
            }
        } else {
            throw new DukeException(this.input + " is not an integer.");
        }
        storage.saveTasks(taskList);
    }
}

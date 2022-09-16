package duke.commands;

import java.io.IOException;

import duke.TaskList;
import duke.exception.DukeException;
import duke.Ui;
import duke.Storage;

/**
 * The ListCommand class encapsulates the execution of a list command.
 */
public class ListCommand extends Command{
    public ListCommand() {}

    /**
     * Executes the list command.
     *
     * @param taskList List from which all the tasks in it are to be listed.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String message = "";
        int length = taskList.length();
        if (length == 0) {
            message += "List of tasks is currently empty.";
        } else {
            message += "Here are the tasks in your list:\n";
            for (int i = 0; i < length; i++) {
                if (i < length - 1) {
                    message += String.format("%d. " + taskList.index(i).toString() + "\n", i + 1);
                } else {
                    message += String.format("%d. " + taskList.index(i).toString(), i + 1);
                }
            }
        }
        return ui.print(message);
    }
}

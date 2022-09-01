package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {

    private String userInput;

    /**
     * Public constructor that stores the input from the user.
     *
     * @param userInput the String input by the user
     */
    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command which marks the indexed task as done.
     * Saves and updates the storage to include the new marked status of the task.
     *
     * @param ui the ui class that prints text in a readable format
     * @param storage the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Ui ui, Storage storage, TaskList taskList) {
        try {
            String reply = taskList.mark(Integer.valueOf(userInput.substring(5)));
            storage.write(taskList.writeTasks());
            return reply;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Please state the index you wish to mark.";
        }
    }
}

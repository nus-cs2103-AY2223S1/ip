package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Marks a task as undone.
 */
public class UnmarkCommand extends Command {

    private String userInput;

    /**
     * Public constructor that stores the input from the user.
     *
     * @param userInput the String input by the user
     */
    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command which un-marks the indexed task from done.
     * Saves and updates the storage to include the new un-marked status of the task.
     *
     * @param ui the ui class that prints text in a readable format
     * @param storage the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Ui ui, Storage storage, TaskList taskList) {
        try {
            String reply = taskList.unmark(Integer.valueOf(userInput.substring(7)));
            storage.write(taskList.writeTasks());
            return reply;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Please state the index you wish to unmark.";
        }
    }
}

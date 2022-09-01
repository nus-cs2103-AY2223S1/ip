package duke.command;

import duke.exception.DukeException;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Manages the finding of certain keywords in a list of tasks.
 */
public class FindCommand extends Command {

    private String userInput;

    /**
     * Public constructor that stores the input from the user.
     *
     * @param userInput the String input by the user
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command which searches for tasks that contain the specified keyword.
     *
     * @param ui the ui class that prints text in a readable format
     * @param storage the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Ui ui, Storage storage, TaskList taskList) {
        try {
            return taskList.find(userInput.substring(5));
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Please state the keywords you wish to find.";
        }
    }
}

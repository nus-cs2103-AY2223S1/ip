package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Manages the finding of certain keywords in a list of tasks.
 */
public class FindCommand extends Command {

    private static final int POSITION_OF_KEYWORD = 5;
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
     * @param storage  the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Storage storage, TaskList taskList) {
        try {
            String keyWord = userInput.substring(POSITION_OF_KEYWORD);
            String reply = taskList.find(keyWord);
            assert reply.startsWith("Here are the matching tasks in your list:") : "Find command replies wrongly!";
            return reply;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Please state the keywords you wish to find.";
        }
    }
}

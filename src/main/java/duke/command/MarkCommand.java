package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {

    private static final int POSITION_OF_TASK_INDEX = 5;
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
     * @param storage  the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Storage storage, TaskList taskList) {
        try {
            Integer taskIndex = Integer.valueOf(userInput.substring(POSITION_OF_TASK_INDEX));
            String reply = taskList.mark(taskIndex);
            assert reply.startsWith("Nice! I've marked this task as done:") : "Mark command replies wrongly!";
            storage.write(taskList.writeTasks());
            return reply;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Please state the index you wish to mark.";
        }
    }
}

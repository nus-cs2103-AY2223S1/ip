package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Manages the deletion of task from a specified task list.
 */
public class DeleteCommand extends Command {

    private static final int POSITION_OF_TASK_INDEX = 7;
    private String userInput;

    /**
     * Public constructor that stores the input from the user.
     *
     * @param userInput the String input by the user
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command and remove an indexed task from the specified task list.
     * Saves and updates the storage to exclude the new task.
     *
     * @param storage  the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Storage storage, TaskList taskList) {
        try {
            Integer taskIndex = Integer.valueOf(userInput.substring(POSITION_OF_TASK_INDEX));
            String reply = taskList.delete(taskIndex);
            assert reply.startsWith("Noted. I've removed this task:") : "Delete command replies wrongly!";
            storage.write(taskList.writeTasks());
            return reply;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Please state the index you wish to delete.";
        }
    }
}

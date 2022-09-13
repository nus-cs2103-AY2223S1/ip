package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to execute deleting a task
 *
 * @author Nephelite
 * @version 0.2
 */
public class DeleteCommand extends Command {
    private String[] splitCommands;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a DeleteCommand
     *
     * @param splitCommands the commands split apart into an array
     * @param tasks TaskList Duke is using
     * @param ui Ui Duke is using
     * @since 0.1
     */
    public DeleteCommand(String[] splitCommands, TaskList tasks, Ui ui) {
        assert(splitCommands != null && tasks != null && ui != null);
        this.splitCommands = splitCommands;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Checks if a String is a number
     *
     * @param string input
     * @return true if string is a number. Otherwise, false
     * @since 0.1
     */
    private boolean isNumber(String string) {
        char[] numberArray = string.toCharArray();
        for (char c : numberArray) {
            if (c < 48 || c > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param storage Duke's storage system for tasks
     * @return Duke's response to the execution of the command
     * @throws DukeException for invalid inputs
     * @since 0.3
     */
    @Override
    public String execute(Storage storage) throws DukeException {
        if (splitCommands.length == 1) {
            throw new DukeException("your command is incomplete."
                    + "\nPlease use the [help] command to check the proper usage of [delete].");
        } else if (splitCommands.length > 2) {
            throw new DukeException("your command has too many arguments."
                    + "\nPlease use the [help] command to check the proper usage of [delete].");
        } else if (!isNumber(splitCommands[1])) {
            throw new DukeException("your command is incorrect."
                    + "\nPlease use the [help] command to check the proper usage of [delete].");
        }
        int taskId = Parser.parseTaskId(splitCommands[1]);
        return checkTaskIdThenDelete(taskId, storage);
    }
    /**
     * Checks if the taskId input is valid, then deletes the task and returns Duke's response
     *
     * @param taskId
     * @param storage
     * @return Duke's response
     * @throws DukeException
     * @since 0.3
     */
    private String checkTaskIdThenDelete(int taskId, Storage storage) throws DukeException {
        if (tasks.size() <= taskId || taskId < 0) {
            throw new DukeException("that task you want to delete does not exist."
                    + "\nUse the [list] command to check what tasks are available.");
        } else {
            return deleteTaskGetResponse(taskId, storage);
        }
    }
    /**
     * Deletes the task corresponding to the taskId, then saves the tasks and returns Duke's response
     *
     * @param taskId
     * @param storage
     * @return Duke's response
     * @since 0.3
     */
    private String deleteTaskGetResponse(int taskId, Storage storage) {
        String response = ui.delete(tasks.getTask(taskId), (tasks.size() - 1));
        tasks.remove(taskId);
        storage.saveDuke(tasks);
        return response;
    }
    /**
     * {@inheritDoc}
     *
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

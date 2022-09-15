package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a command as done
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to mark this task done: ";

    private int taskIndex;

    /**
     * Constructs a MarkCommand instance
     *
     * @param taskIndex the index of the task to be marked as done
     */
    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Returns a boolean value true if the command is a bye command,
     * false otherwise.
     *
     * @return a boolean value on whether the command is a bye command
     */
    @Override
    public boolean isByeCommand() {
        return false;
    }

    /**
     * Executes the command to mark the specified task as done
     *
     * @param tasks The current list of tasks
     * @param ui The Ui instance to return the result to the user
     * @param storage The Storage instance to store the result to local storage
     * @return the string representation of the execution result
     * @throws DukeException if task is not found, or cannot be marked
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markAsDone(taskIndex);
            String result = getResultString(tasks);
            ui.showMessage(result);
            return result;
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Gets the string representation of the result to be returned to the user
     *
     * @param tasks An ArrayList of tasks
     * @return A string presentation of the arraylist and the tasks
     */
    public String getResultString(TaskList tasks) throws DukeException {
        return MESSAGE_SUCCESS + System.lineSeparator() + tasks.getTask(taskIndex) + tasks.showNumberOfTasks();
    }
}
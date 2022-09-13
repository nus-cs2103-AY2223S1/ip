package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command to delete a task from the list of tasks
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to delete this task: \n";

    private int taskIndex;

    /**
     * Constructs a DeleteCommand instance
     *
     * @param taskIndex the index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
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
     * Executes the command to delete the specified task from the list of tasks
     *
     * @param tasks The current list of tasks
     * @param ui The Ui instance to return the result to the user
     * @param storage The Storage instance to store the result to local storage
     * @return the string representation of the execution result
     * @throws DukeException if task is not found
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String t = tasks.getTask(taskIndex);
            String result = MESSAGE_SUCCESS + t + System.lineSeparator() + tasks.showNumberOfTasks();
            tasks.deleteTask(taskIndex);
            ui.showMessage(result);
            return result;
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
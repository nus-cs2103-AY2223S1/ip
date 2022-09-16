package duke.commands;

import duke.exceptions.DukeException;
import duke.massops.MassOperation;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a task as not done
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private static final String MESSAGE_SUCCESS = "Woof! I managed to mark the following task(s) as not done:";

    private MassOperation massOperation;

    /**
     * Constructs an UnmarkCommand instance
     *
     * @param massOperation the mass operation specifying the range of the unmark operation
     */
    public UnmarkCommand(MassOperation massOperation) {
        super();
        this.massOperation = massOperation;
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
     * Executes the command to mark the specified task as not done
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
            String tasksToShow = tasks.unmarkBasedOnMassOperation(massOperation);
            String result = getResultString(tasks, tasksToShow);
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
     * @param tasksToShow A string representation of the tasks to be shown to the user
     * @return A string presentation of the arraylist and the tasks
     */
    public String getResultString(TaskList tasks, String tasksToShow) throws DukeException {
        return MESSAGE_SUCCESS + System.lineSeparator() + tasksToShow + tasks.showNumberOfTasks();
    }
}
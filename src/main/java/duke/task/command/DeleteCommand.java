package duke.task.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Delete Command, to delete a task from tasks.
 *
 * @author Elgin
 */
public class DeleteCommand extends Command {
    /** The arguments of the command (e.g. 'sleep /at 2020-12-12'). */
    private static String arguments;

    /**
     * Constructor of DeleteCommand.
     *
     * @param arguments The arguments of the command.
     */
    public DeleteCommand(String arguments) {
        DeleteCommand.arguments = arguments;
    }

    /**
     * Executes the delete command and remove a task from tasks.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     * @return Duke's message to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String deletedTaskDesc = tasks.deleteItem(DeleteCommand.arguments);

        assert !deletedTaskDesc.isEmpty() : "Deleted task description should never be empty";

        return ui.getTaskDeletedMsg(tasks.getTaskLen(), deletedTaskDesc);
    }
}

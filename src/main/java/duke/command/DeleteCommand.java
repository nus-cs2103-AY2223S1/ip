package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * The class that encapsulates the delete command.
 */
public class DeleteCommand extends Command {

    /** The index of the task to delete in string */
    private final String indexString;

    /**
     * The class constructor.
     *
     * @param indexString The index of the task to
     *                    delete in string.
     */
    public DeleteCommand(String indexString) {
        this.indexString = indexString;
    }

    /**
     * Handles the execution behaviour of the delete command.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke bot.
     * @param storage The storage of data.
     * @throws DukeException If there is an error that arise from the
     * delete command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(indexString);
        storage.saveData(tasks);
        ui.printMessage("Noted. I've removed this task:\n" + deletedTask
                + "\nNow you have " + tasks.size() + " tasks.");
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    @Override
    public String getCommand() {
        return "delete";
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return "delete " + indexString;
    }
}

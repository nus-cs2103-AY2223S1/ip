package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * A class that encapsulates the mark command.
 */
public class MarkCommand extends Command {
    /** The index of the task in string */
    private final String indexString;
    /** The completion status of the task to be set to */
    private final boolean isComplete;

    /**
     * The class constructor.
     *
     * @param indexString The index of the task in string.
     * @param isComplete The completion status of the task
     *                   to be set to.
     */
    public MarkCommand(String indexString, boolean isComplete) {
        this.indexString = indexString;
        this.isComplete = isComplete;
    }

    /**
     * Handles the execution behaviour of the mark command.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke bot.
     * @param storage The storage of data.
     * @throws DukeException If there is an error that arise from the
     *                       mark command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.setTaskCompletion(indexString, isComplete);
        storage.saveData(tasks);
        if (isComplete) {
            ui.printMessage("Nice! I've marked this task as done:\n" + task);
        } else {
            ui.printMessage("OK, I've marked this task as not done yet:\n" + task);
        }
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    @Override
    public String getCommand() {
        return "mark";
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return "mark " + indexString + " " + isComplete;
    }
}

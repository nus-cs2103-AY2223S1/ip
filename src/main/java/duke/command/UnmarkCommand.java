package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an Unmark Command, which unmarks a task as completed.
 *
 * @author Elgin
 */
public class UnmarkCommand extends Command {
    /** The arguments of the command (e.g. 'sleep /at 2020-12-12'). */
    private final String arguments;

    /**
     * Constructor of MarkCommand.
     *
     * @param arguments The arguments of the command.
     */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the Unmark command, and unmarks the task as not done.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     * @return Duke's message to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String taskDescription = tasks.markOrUnmark(this.arguments, false);

        return ui.taskUnmarkedMsg(taskDescription);
    }
}

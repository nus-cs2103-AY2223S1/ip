package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import exceptions.DukeException;
import exceptions.InvalidTaskIndexException;
import task.Task;
import ui.Ui;

/**
 * Removes the tag of a task.
 */
public class UntagCommand extends Command {
    public static final String SYNTAX = "untag TASK_NUMBER";

    private final String[] inputStrings;

    /**
     * Constructs an untag command, which removes the tag of the task at the specified task index.
     *
     * @param inputStrings The specified input strings.
     */
    public UntagCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Removes the tag of a task.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed.
            int taskIndex = Integer.parseInt(inputStrings[1].trim()) - 1;

            Task task = tasks.getTask(taskIndex);
            task.clearTag();

            return ui.showUntagTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            throw new InvalidTaskIndexException();
        }
    }

    public boolean isExit() {
        return false;
    }
}

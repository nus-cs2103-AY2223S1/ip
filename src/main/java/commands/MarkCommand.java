package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import exceptions.DukeException;
import exceptions.InvalidTaskIndexException;
import task.Task;
import ui.Ui;

/**
 * Marks the specified task as done,
 */
public class MarkCommand extends Command {
    public static final String SYNTAX = "mark TASK_NUMBER";

    private final String[] inputStrings;

    /**
     * Constructs a mark command, which marks the task with the input index as done.
     *
     * @param inputStrings The specified input strings.
     */
    public MarkCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Marks the task as complete.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed.
            int taskIndex = Integer.parseInt(inputStrings[1].trim()) - 1;

            Task task = tasks.getTask(taskIndex);
            task.markTask();
            return ui.showMarkTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            throw new InvalidTaskIndexException();
        }
    }

    public boolean isExit() {
        return false;
    }
}

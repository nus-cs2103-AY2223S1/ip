package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import task.Task;

/**
 * Marks the specified task as done
 */
public class MarkCommand extends Command {
    private final String[] inputStrings;

    /**
     * Constructs a mark command, which marks the task with the input index as done
     *
     * @param inputStrings The specified input strings.
     */
    public MarkCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Marks the task as complete
     * <p>
     * {@inheritDoc}
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
            // Hence, we need to account for this offset here
            int taskIndex = Integer.parseInt(inputStrings[1]) - 1;

            Task task = tasks.getTask(taskIndex);
            task.markTask();
            ui.showMarkTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            throw new DukeException("     ☹ OOPS!!! The index specified is invalid.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}

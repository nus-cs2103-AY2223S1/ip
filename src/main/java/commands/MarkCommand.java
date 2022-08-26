package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import task.Task;

/**
 * Marks the specified task as done,
 */
public class MarkCommand extends Command {
    private final String[] inputStrings;

    public MarkCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed.
            int taskIndex = Integer.parseInt(inputStrings[1]) - 1;

            Task task = tasks.getTask(taskIndex);
            task.markTask();
            ui.showMarkTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            throw new DukeException("     ☹ OOPS!!! The index specified is invalid.");
        }
    }

    public boolean isExit() {
        return false;
    }
}

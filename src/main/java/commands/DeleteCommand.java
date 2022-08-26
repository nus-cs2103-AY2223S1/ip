package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import task.Task;

/**
 * Deletes the specified task.
 */
public class DeleteCommand extends Command {
    private final String[] inputStrings;

    public DeleteCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Tasks are displayed as 1-indexed, but they are stored as 0-indexed.
        int taskIndex = Integer.parseInt(inputStrings[1]) - 1;
        Task task = tasks.removeTask(taskIndex);

        ui.showRemoveTask(task, tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}

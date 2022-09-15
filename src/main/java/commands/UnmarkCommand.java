package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;
import task.Task;

/**
 * A command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {

    private final int index;
    private Task task;

    /**
     * Creates an UnmarkCommand with the index of the Task to be marked as incomplete.
     *
     * @param index the index of the Task to be marked as incomplete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        this.task = tasks.unmark(this.index);
        String response = generateResponse(tasks);
        ui.setOutput(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        return String.format("Alright, this task is now marked as incomplete:\n%s\n", this.task)
                + "Work on it soon..?\n";
    }
}

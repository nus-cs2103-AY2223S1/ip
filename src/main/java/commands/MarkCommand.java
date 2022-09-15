package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;
import task.Task;

/**
 * A command to mark a task as done.
 */
public class MarkCommand extends Command {

    private final int index;
    private Task task;

    /**
     * Creates a MarkCommand with the index of the Task to be marked as done.
     *
     * @param index the index of the Task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        this.task = tasks.mark(this.index);
        String response = generateResponse(tasks);
        ui.setOutput(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        return String.format("Good job for completing the task!\nThis task is now marked as done:\n%s\n", this.task);
    }
}

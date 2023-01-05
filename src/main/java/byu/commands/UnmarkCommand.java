package byu.commands;

import byu.exceptions.InvalidIndexException;
import byu.task.Task;
import byu.util.Response;
import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {

    private final int index;
    private Task task;

    /**
     * Creates an UnmarkCommand with the index of the {@code Task} to be marked as incomplete.
     *
     * @param index the index of the {@code Task} to be marked as incomplete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        this.task = tasks.unmark(this.index);
    }

    @Override
    public Response generateResponse(TaskList tasks) {
        String output = String.format("Alright, this task is now marked as incomplete:\n%s\n", this.task)
                + "Work on it soon..?\n";
        return new Response(output, false, false);
    }
}

package byu.commands;

import byu.exceptions.InvalidIndexException;
import byu.task.Task;
import byu.util.Response;
import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to delete a task in the list.
 */
public class DeleteCommand extends Command {

    private final int index;
    private Task task;

    /**
     * Creates a DeleteCommand with the index of the {@code Task} to be deleted.
     *
     * @param index the index of the {@code Task} to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        task = tasks.delete(this.index);
    }

    @Override
    public Response generateResponse(TaskList tasks) {
        String removeResponse = "Yay! I've removed this task:\n%s\n";
        String summaryForOneTask = "Now you have %d upcoming task.\n";
        String summaryForManyTasks = "Now you have %d upcoming tasks.\n";
        String output;

        int numOfTasks = tasks.getNumOfTasks();
        if (numOfTasks == 1) {
            output = String.format(removeResponse + summaryForOneTask, task, numOfTasks);
        } else {
            output = String.format(removeResponse + summaryForManyTasks, task, numOfTasks);
        }
        return new Response(output, false, false);
    }
}

package byu.commands;

import byu.exceptions.InvalidIndexException;
import byu.task.Task;
import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to delete a task in the list.
 */
public class DeleteCommand extends Command {

    private final int index;
    private Task task;

    /**
     * Creates a DeleteCommand with the index of the Task to be deleted.
     *
     * @param index the index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        task = tasks.delete(this.index);
        String response = generateResponse(tasks);
        ui.setOutput(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        String removeResponse = "Yay! I've removed this task:\n%s\n";
        String summaryForOneTask = "Now you have %d upcoming task.\n";
        String summaryForManyTasks = "Now you have %d upcoming tasks.\n";

        int numOfTasks = tasks.getNumOfTasks();
        if (numOfTasks == 1) {
            return String.format(removeResponse + summaryForOneTask, task, numOfTasks);
        } else {
            return String.format(removeResponse + summaryForManyTasks, task, numOfTasks);
        }
    }
}

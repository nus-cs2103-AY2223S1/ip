package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Delete a task when command is called.
 */
public class DeleteCommand extends Command {
    private int taskNum;

     /**
     * Delete the task based on the index entered when command is called.
     *
     * @param taskNum index of task to be deleted
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 0 || taskNum >= tasks.size() + 1) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }

        Task deletedTask = tasks.get(taskNum - 1);
        tasks.delete(taskNum - 1);
        ui.printMessage("\tNoted. I've removed this task:\n\t" +
                deletedTask.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAllTasks());
    }
}

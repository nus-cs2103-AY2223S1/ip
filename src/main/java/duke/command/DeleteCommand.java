package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Deletes a task when command is called.
 */
public class DeleteCommand extends Command {
    private final int taskNum;

     /**
     * Deletes the task based on the index entered when command is called.
     *
     * @param taskNum index of task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new DukeException("◔ ‸◔？ The index of the task is not in the list :(");
        }

        Task deletedTask = tasks.get(taskNum - 1);
        tasks.delete(taskNum - 1);
        ui.outputMessage("Okay! (๑´ڡ`๑) I've removed this task:\n"
                + deletedTask.toString()
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list!");
        storage.save(tasks);
    }
}

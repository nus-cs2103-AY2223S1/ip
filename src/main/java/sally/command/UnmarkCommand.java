package sally.command;

import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.Task;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * UnmarkCommand class to represent command to unmark a task in the tasklist.
 *
 * @author liviamil
 */

public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Unmarks a task
     *
     * @param index of the task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index= index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SallyException {
        if (index >= tasks.getNumOfTasks()) {
            throw new SallyException("Oops! Enter a valid task number!");
        }
        Task task = tasks.getTask(index);
        String taskInString = task.toString();
        if (task.getDoneStatus()) {
            task.markAsUndone();
            String unmarkTask = task.toString();
            ui.showUnmarked(unmarkTask);
            try {
                storage.savesFile(tasks);
            } catch (SallyException e) {
                System.out.println("Oops! File Not Found");
            }
        } else {
            ui.showPreviouslyUnmarked(taskInString);
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }
}

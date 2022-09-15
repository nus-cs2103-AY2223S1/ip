package sally.command;

import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.Task;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * MarkCommand class to represent command to mark a task in the tasklist.
 *
 * @author liviamil
 */

public class MarkCommand extends Command {
    private final int index;

    /**
     * Marks a given task
     *
     * @param index of task to be marked
     */
    public MarkCommand(int index) {
        this.index= index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        String taskInString = task.toString();
        ui.displaySally("index = " + index);
        ui.displaySally("task = " + taskInString);
        ui.displaySally("task.getDoneStatus() = " + task.getDoneStatus());
        if (!task.getDoneStatus()) {
            task.markAsDone();
            String markTask = task.toString();
            ui.showMarked(markTask);
            try {
                storage.savesFile(tasks);
            } catch (SallyException e) {
                System.out.println("File Not Found");
            }
        } else {
            ui.showPreviouslyMarked(taskInString);
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }
}

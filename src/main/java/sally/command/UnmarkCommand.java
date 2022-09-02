package sally.command;

import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.Task;
import sally.task.TaskList;
import sally.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index= index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
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

package sally.command;

import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.TaskList;
import sally.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index= index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (0 <= index && index < tasks.getNumOfTasks()) {
            String removed = tasks.getTask(index).toString();
            tasks.deleteTask(index);
            ui.showDeleted(removed);
        }
        try {
            storage.savesFile(tasks);
        } catch (SallyException e) {
            System.out.println("Oops! File Not Found");
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }
}

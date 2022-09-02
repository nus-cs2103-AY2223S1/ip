package sally.command;

import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.TaskList;
import sally.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {};

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.savesFile(tasks);
        } catch (SallyException e) {
            System.out.println("Oops! File Not Found");
        }
    }

    @Override
    public boolean isBye() {
        return true;
    }
}

package command;

import exception.MeowerException;
import task.Task;
import meower.Storage;
import meower.TaskList;
import meower.Ui;

public class ClearCommand extends Command {
    
    public ClearCommand() {
        super();
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException {
        int numOfArchives = storage.clearArchive();
        return ui.clear(numOfArchives);
    }

    public Task getTask() {
        return Task.empty();
    }
}

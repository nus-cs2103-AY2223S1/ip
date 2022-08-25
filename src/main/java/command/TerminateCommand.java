package command;

import tasklist.TaskList;
import util.Storage;

public class TerminateCommand extends Command{
    @Override
    public void execute(TaskList list, Storage storage) {
        isTerminated = true;
        storage.saveAllTasks(list);
    }
}

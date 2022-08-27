package command;

import tasklist.TaskList;
import util.Storage;

/**
 * Represents a command that to be executed that terminates the program
 *
 * @author Bryan Lim Jing Xiang
 */
public class TerminateCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        isTerminated = true;
        storage.saveAllTasks(list);
    }
}
